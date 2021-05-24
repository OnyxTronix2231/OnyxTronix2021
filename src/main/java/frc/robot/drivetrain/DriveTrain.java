package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

import static frc.robot.drivetrain.DriveTrainConstants.*;
import static frc.robot.drivetrain.DriveTrainConstants.TrajectoryConstants.ENCODER_CPR;
import static frc.robot.drivetrain.DriveTrainConstants.TrajectoryConstants.START_POSE;

public class DriveTrain extends SubsystemBase {

    private final DriveTrainComponents components;
    private final SimulationDriveTrainComponents simulationComponents;
    private final DriveTrainVirtualComponents virtualComponents;

    public DriveTrain(DriveTrainComponents components, SimulationDriveTrainComponents simulationComponents,
                      DriveTrainVirtualComponents virtualComponents) {
        this.components = components;
        this.simulationComponents = simulationComponents;
        this.virtualComponents = virtualComponents;

        if (Robot.isSimulation()) {
            Shuffleboard.getTab("DriveTrain").add("Field", getField2d());
            Shuffleboard.getTab("DriveTrain").addNumber("actualVoltage",
                    () -> getSimRightMaster().getMotorOutputVoltage());
            Shuffleboard.getTab("DriveTrain").addNumber("speed",
                    () -> encoderUnitsDeciSecToMetersSec(getSimLeftMaster().getSelectedSensorVelocity()));
            getField2d().setRobotPose(START_POSE);
            getDriveTrainSim().setPose(START_POSE);
        }
        Shuffleboard.getTab("DriveTrain").addNumber("CURRENT X",
                () -> virtualComponents.getOdometry().getPoseMeters().getX());
        Shuffleboard.getTab("DriveTrain").addNumber("CURRENT Y",
                () -> virtualComponents.getOdometry().getPoseMeters().getY());
        Shuffleboard.getTab("DriveTrain").addNumber("CURRENT ROTATION DEGREES",
                () -> virtualComponents.getOdometry().getPoseMeters().getRotation().getDegrees());

        resetHeading();
        resetOdometryToPose(START_POSE);
        resetEncoders();
    }

    @Override
    public void periodic() {
        virtualComponents.getOdometry().update(
                Rotation2d.fromDegrees(Robot.isSimulation() ? getHeading() : -getHeading()),
                encoderUnitsToMeters(Robot.isSimulation() ? getSimLeftMaster().getSelectedSensorPosition() :
                        getLeftMaster().getSelectedSensorPosition()),
                encoderUnitsToMeters(Robot.isSimulation() ? getSimRightMaster().getSelectedSensorPosition() :
                        getRightMaster().getSelectedSensorPosition()));
    }

    @Override
    public void simulationPeriodic() {
        getField2d().setRobotPose(virtualComponents.getOdometry().getPoseMeters());
        getDriveTrainSim().setInputs(
                getSimLeftMaster().getMotorOutputPercent() * RobotController.getBatteryVoltage(),
                getSimRightMaster().getMotorOutputPercent() * RobotController.getBatteryVoltage());
        getDriveTrainSim().update(0.02);

        getSimLeftMaster().getSimCollection().setQuadratureVelocity(
                (int) metersSecToEncoderUnitsDeciSec(getDriveTrainSim().getLeftVelocityMetersPerSecond()));
        getSimRightMaster().getSimCollection().setQuadratureVelocity(
                (int) metersSecToEncoderUnitsDeciSec(getDriveTrainSim().getRightVelocityMetersPerSecond()));

        getSimLeftMaster().getSimCollection().setQuadratureRawPosition(
                (int) metersToEncoderUnits(getDriveTrainSim().getLeftPositionMeters()));
        getSimRightMaster().getSimCollection().setQuadratureRawPosition(
                (int) metersToEncoderUnits(getDriveTrainSim().getRightPositionMeters()));

        simulationComponents.getAnalogGyroSim().setAngle(getDriveTrainSim().getHeading().getDegrees());
    }

    public void arcadeDrive(final double forwardSpeed, final double rotationSpeed) {
        if (Robot.isReal()) {
            virtualComponents.getDifferentialDrive().arcadeDrive(forwardSpeed * ARCADE_DRIVE_FORWARD_SENSITIVITY,
                    rotationSpeed * ARCADE_DRIVE_ROTATION_SENSITIVITY, false);
        } else {
            virtualComponents.getSimDifferentialDrive().arcadeDrive(forwardSpeed * ARCADE_DRIVE_FORWARD_SENSITIVITY,
                    rotationSpeed * ARCADE_DRIVE_ROTATION_SENSITIVITY, false);
        }
    }

    public Pose2d getPose() {
        return virtualComponents.getOdometry().getPoseMeters();
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return Robot.isSimulation() ?
                new DifferentialDriveWheelSpeeds(getSimLeftMaster().getSelectedSensorVelocity(),
                        getSimRightMaster().getSelectedSensorVelocity()) :
                new DifferentialDriveWheelSpeeds(getLeftMaster().getSelectedSensorVelocity(),
                        getRightMaster().getSelectedSensorVelocity());
    }

    public void tankDriveVolts(double leftVolts, double rightVolts) {
        if (Robot.isReal()) {
            getLeftMaster().set(leftVolts / 12);
            getRightMaster().set(rightVolts / 12);
            virtualComponents.getDifferentialDrive().feed();
        } else {
            getSimLeftMaster().set(leftVolts / 12);
            getSimRightMaster().set(rightVolts / 12);
            virtualComponents.getSimDifferentialDrive().feed();
        }
    }

    public double getAverageEncoderDistance() {
        return Robot.isSimulation() ? (getSimLeftMaster().getSelectedSensorPosition() +
                getSimRightMaster().getSelectedSensorPosition()) / 2 : (getLeftMaster().getSelectedSensorPosition() +
                getRightMaster().getSelectedSensorPosition()) / 2;
    }

    public void setArcadeDriveMaxOutput(double maxOutput) {
        if (Robot.isReal()) {
            virtualComponents.getDifferentialDrive().setMaxOutput(maxOutput);
        } else {
            virtualComponents.getSimDifferentialDrive().setMaxOutput(maxOutput);
        }
    }

    public void resetHeading() {
        if (Robot.isReal()) {
            components.getNormelizedPigeonIMU().setYaw(0);
        } else {
            simulationComponents.getAnalogGyroSim().setAngle(0);
        }
    }

    public double getHeading() {
        return Robot.isSimulation() ? simulationComponents.getAnalogGyroSim().getAngle() :
                components.getNormelizedPigeonIMU().getNormalizedYaw();
    }

    public double getYawRate() {
        return Robot.isSimulation() ? simulationComponents.getAnalogGyroSim().getRate() :
                components.getNormelizedPigeonIMU().getYawRate();
    }

    public void stopDrive() {
        if (Robot.isReal()) {
            virtualComponents.getDifferentialDrive().stopMotor();
        } else {
            virtualComponents.getSimDifferentialDrive().stopMotor();
        }
    }

    public void setNeutralModeToCoast() {
        setNeutralMode(NeutralMode.Coast);
    }

    public void setNeutralModeToBrake() {
        setNeutralMode(NeutralMode.Brake);
    }

    private double metersToEncoderUnits(double meters) {
        return meters * ENCODER_CPR / PERIMETER_METER;
    }

    private double metersSecToEncoderUnitsDeciSec(double metersSec) {
        return metersToEncoderUnits(metersSec / DECISECOND_IN_SECOND);
    }

    private double encoderUnitsToMeters(double units) {
        return units * PERIMETER_METER / ENCODER_CPR;
    }

    private double encoderUnitsDeciSecToMetersSec(double unitsDeciSec) {
        return encoderUnitsToMeters(unitsDeciSec * DECISECOND_IN_SECOND);
    }

    private WPI_TalonFX getLeftMaster() {
        return components.getLeftMasterMotor();
    }

    private WPI_TalonSRX getSimLeftMaster() {
        return simulationComponents.getLeftMasterMotor();
    }

    private WPI_TalonFX getRightMaster() {
        return components.getRightMasterMotor();
    }

    private WPI_TalonSRX getSimRightMaster() {
        return simulationComponents.getRightMasterMotor();
    }

    private Field2d getField2d() {
        return simulationComponents.getField2d();
    }

    private DifferentialDrivetrainSim getDriveTrainSim() {
        return virtualComponents.getDriveTrainSim();
    }

    private void setNeutralMode(NeutralMode mode) {
        if (Robot.isReal()) {
            getLeftMaster().setNeutralMode(mode);
            components.getLeftSlaveMotor().setNeutralMode(mode);
            getRightMaster().setNeutralMode(mode);
            components.getRightSlaveMotor().setNeutralMode(mode);
        } else {
            getSimLeftMaster().setNeutralMode(mode);
            simulationComponents.getLeftSlaveMotor().setNeutralMode(mode);
            getSimRightMaster().setNeutralMode(mode);
            simulationComponents.getRightSlaveMotor().setNeutralMode(mode);
        }
    }

    private void resetEncoders() {
        if (Robot.isReal()) {
            getLeftMaster().setSelectedSensorPosition(0);
            getRightMaster().setSelectedSensorPosition(0);
        } else {
            getSimLeftMaster().setSelectedSensorPosition(0);
            getSimRightMaster().setSelectedSensorPosition(0);
        }
    }

    public void resetSimOdometryToPose(Pose2d pose) {//For future Vision integration - will delete comment pre-merge
        getSimRightMaster().getSimCollection().setQuadratureVelocity(0);
        getSimLeftMaster().getSimCollection().setQuadratureVelocity(0);
        simulationComponents.getAnalogGyroSim().setRate(pose.getRotation().getDegrees());
        simulationComponents.getAnalogGyroSim().setAngle(pose.getRotation().getDegrees());
        simulationComponents.getField2d().setRobotPose(pose);
        virtualComponents.getDriveTrainSim().setPose(pose);
        virtualComponents.getDriveTrainSim().setInputs(0, 0);
    }

    public void resetOdometryToPose(Pose2d pose) {
        resetEncoders();
        virtualComponents.getOdometry().resetPosition(pose, pose.getRotation());
        if (Robot.isReal())
            components.getNormelizedPigeonIMU().setYaw(pose.getRotation().getDegrees());
        else
            resetSimOdometryToPose(pose);
    }
}

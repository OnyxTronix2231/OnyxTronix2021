package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
        Shuffleboard.getTab("DriveTrain").add("Field", getField2d());
        getField2d().setRobotPose(START_POSE);
        virtualComponents.getOdometry().resetPosition(START_POSE, START_POSE.getRotation());
        resetEncoders();
        if (Robot.isSimulation()) {
            simulationComponents.getLeftMasterMotor().setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 10);
            simulationComponents.getRightMasterMotor().setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 10);
        }
    }

    @Override
    public void periodic() {
        virtualComponents.getOdometry().update(
                Rotation2d.fromDegrees(getHeading()),
                encoderUnitsToMeters(getSimLeftMaster().getSelectedSensorPosition()),
                encoderUnitsToMeters(getSimRightMaster().getSelectedSensorPosition()));

        getField2d().setRobotPose(virtualComponents.getOdometry().getPoseMeters());
    }

    @Override
    public void simulationPeriodic() {
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
        virtualComponents.getDifferentialDrive().arcadeDrive(forwardSpeed * ARCADE_DRIVE_FORWARD_SENSITIVITY,
                rotationSpeed * ARCADE_DRIVE_ROTATION_SENSITIVITY, false);
        virtualComponents.getSimDifferentialDrive().arcadeDrive(forwardSpeed * ARCADE_DRIVE_FORWARD_SENSITIVITY,
                rotationSpeed * ARCADE_DRIVE_ROTATION_SENSITIVITY, false);
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

    public void resetOdometry(Pose2d pose) {
        virtualComponents.getOdometry().resetPosition(pose,
                Rotation2d.fromDegrees(getHeading()));
    }

    public void tankDriveVolts(double leftVolts, double rightVolts) {
        simulationComponents.getLeftMotors().setVoltage(leftVolts);
        simulationComponents.getRightMotors().setVoltage(rightVolts);
        virtualComponents.getSimDifferentialDrive().feed();
        components.getLeftMotors().setVoltage(leftVolts);
        components.getRightMotors().setVoltage(rightVolts);
        virtualComponents.getDifferentialDrive().feed();
    }

    public double getAverageEncoderDistance() {
        return Robot.isSimulation() ? (getSimLeftMaster().getSelectedSensorPosition() +
                getSimRightMaster().getSelectedSensorPosition()) / 2 : (getLeftMaster().getSelectedSensorPosition() +
                getRightMaster().getSelectedSensorPosition()) / 2;
    }

    public void setMaxOutputArcadeDrive(double maxOutput) {
        virtualComponents.getDifferentialDrive().setMaxOutput(maxOutput);
        virtualComponents.getSimDifferentialDrive().setMaxOutput(maxOutput);
    }

    public void zeroHeading() {
        components.getNormelizedPigeonIMU().setYaw(0);
        simulationComponents.getAnalogGyroSim().setAngle(0);
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
        virtualComponents.getDifferentialDrive().stopMotor();
        virtualComponents.getSimDifferentialDrive().stopMotor();
    }

    public void setNeutralModeToCoast() {
        components.getLeftMasterMotor().setNeutralMode(NeutralMode.Coast);
        components.getLeftSlaveMotor().setNeutralMode(NeutralMode.Coast);
        components.getRightMasterMotor().setNeutralMode(NeutralMode.Coast);
        components.getRightSlaveMotor().setNeutralMode(NeutralMode.Coast);
    }

    public void setNeutralModeToBrake() {
        components.getLeftMasterMotor().setNeutralMode(NeutralMode.Brake);
        components.getLeftSlaveMotor().setNeutralMode(NeutralMode.Brake);
        components.getRightMasterMotor().setNeutralMode(NeutralMode.Brake);
        components.getRightSlaveMotor().setNeutralMode(NeutralMode.Brake);
    }

    private double metersToEncoderUnits(double meters) {
        return meters * ENCODER_CPR / PERIMETER_METER;
    }

    private double metersSecToEncoderUnitsDeciSec(double metersSec) {
        return metersToEncoderUnits(metersSec / 10);
    }

    private double encoderUnitsToMeters(double units) {
        return units * PERIMETER_METER / ENCODER_CPR;
    }

    private double encoderUnitsDeciSecToMetersSec(double unitsDeciSec) {
        return encoderUnitsToMeters(unitsDeciSec * 10);
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

    private void resetEncoders() {
        getLeftMaster().setSelectedSensorPosition(0);
        getRightMaster().setSelectedSensorPosition(0);
        getSimLeftMaster().setSelectedSensorPosition(0);
        getSimRightMaster().setSelectedSensorPosition(0);
    }

    public void resetSimOdometryToPose(Pose2d pose) {//For future Vision integration - will delete comment pre-merge
        resetEncoders();
        virtualComponents.getOdometry().resetPosition(pose, pose.getRotation());
        simulationComponents.getRightMasterMotor().getSimCollection().setQuadratureVelocity(0);
        simulationComponents.getLeftMasterMotor().getSimCollection().setQuadratureVelocity(0);
        simulationComponents.getAnalogGyroSim().setRate(0);
        simulationComponents.getAnalogGyroSim().setAngle(0);
        simulationComponents.getField2d().setRobotPose(pose);
        virtualComponents.getDriveTrainSim().setPose(pose);
        virtualComponents.getDriveTrainSim().setInputs(0, 0);
    }
}

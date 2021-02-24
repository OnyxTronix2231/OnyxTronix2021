package frc.robot.drivetrain;

import static frc.robot.drivetrain.DriveTrainConstants.ARCADE_DRIVE_FORWARD_SENSITIVITY;
import static frc.robot.drivetrain.DriveTrainConstants.ARCADE_DRIVE_ROTATION_SENSITIVITY;
import static frc.robot.drivetrain.DriveTrainConstants.DECISECOND_IN_SECOND;
import static frc.robot.drivetrain.DriveTrainConstants.PERIMETER_METER;
import static frc.robot.drivetrain.DriveTrainConstants.TrajectoryConstants.ENCODER_CPR;
import static frc.robot.drivetrain.DriveTrainConstants.TrajectoryConstants.START_POSE;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

import java.io.File;
import java.io.PrintWriter;

public class DriveTrain extends SubsystemBase {

    private final DriveTrainComponents components;
    private final SimulationDriveTrainComponents simulationComponents;
    private final DriveTrainVirtualComponents virtualComponents;
    private final NetworkTableEntry voltageInputEntry;
    private final NetworkTableEntry resetEntry;
    private final Timer kaTimer;
    private PrintWriter writer;
    private double maxSpeedAtVoltage;

    public DriveTrain(DriveTrainComponents components, SimulationDriveTrainComponents simulationComponents,
                      DriveTrainVirtualComponents virtualComponents) {
        this.components = components;
        this.simulationComponents = simulationComponents;
        this.virtualComponents = virtualComponents;
        voltageInputEntry = Shuffleboard.getTab("DriveTrain").add("VoltageInput", 0).getEntry();
        kaTimer = new Timer();
        maxSpeedAtVoltage = 0;
        resetEntry = Shuffleboard.getTab("DriveTrain").add("Reset", 0).getEntry();
        initKaTimer();
        if (Robot.isSimulation()) {
            Shuffleboard.getTab("DriveTrain").add("Field", getField2d());
            Shuffleboard.getTab("DriveTrain").addNumber("actualVoltage",
                    () -> getSimRightMaster().getMotorOutputVoltage());
            Shuffleboard.getTab("DriveTrain").addNumber("speed",
                    () -> encoderUnitsDeciSecToMetersSec(getSimLeftMaster().getSelectedSensorVelocity()));
            getField2d().setRobotPose(START_POSE);
            getDriveTrainSim().setPose(START_POSE);
        }
        else {
            Shuffleboard.getTab("DriveTrain").addNumber("actualVoltage",
                    () -> getLeftMaster().getMotorOutputVoltage());
            Shuffleboard.getTab("DriveTrain").addNumber("speed",
                    () -> encoderUnitsDeciSecToMetersSec(getLeftMaster().getSelectedSensorVelocity()));
        }
        Shuffleboard.getTab("DriveTrain").addNumber("Current Left Master",
                ()-> getLeftMaster().getStatorCurrent());
        Shuffleboard.getTab("DriveTrain").addNumber("Current Right Master",
                ()-> getRightMaster().getStatorCurrent());
        Shuffleboard.getTab("DriveTrain").addNumber("Current Left Slave",
                ()-> ((TalonFX)(components.getLeftSlaveMotor())).getStatorCurrent());
        Shuffleboard.getTab("DriveTrain").addNumber("Current Right Slave",
                ()-> ((TalonFX)(components.getRightSlaveMotor())).getStatorCurrent());
        Shuffleboard.getTab("DriveTrain").addNumber("Current Diff",
                ()-> (getLeftMaster().getStatorCurrent() + ((TalonFX)components.getLeftSlaveMotor()).getStatorCurrent()
                        - (getRightMaster().getStatorCurrent() + ((TalonFX)(components.getRightSlaveMotor())).getStatorCurrent())));
        virtualComponents.getOdometry().resetPosition(START_POSE, START_POSE.getRotation());
        resetEncoders();
        try {
            writer = new PrintWriter("/home/lvuser/Output.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getShuffleboardVoltage() {
        return voltageInputEntry.getDouble(0);
    }

    @Override
    public void periodic() {
        double speed = encoderUnitsDeciSecToMetersSec(getLeftMaster().getSelectedSensorVelocity());
        if (resetEntry.getDouble(0) == 1)
            maxSpeedAtVoltage = 0;
        else if (speed > maxSpeedAtVoltage)
            maxSpeedAtVoltage = speed;
//        System.out.println(maxSpeedAtVoltage);
//        System.out.println(getLeftMaster().getSupplyCurrent());
        virtualComponents.getOdometry().update(
                Rotation2d.fromDegrees(getHeading()),
                encoderUnitsToMeters(Robot.isSimulation() ? getSimLeftMaster().getSelectedSensorPosition() :
                        getLeftMaster().getSelectedSensorPosition()),
                encoderUnitsToMeters(Robot.isSimulation() ? getSimRightMaster().getSelectedSensorPosition() :
                        getRightMaster().getSelectedSensorPosition()));
        kaPrints();

        //System.out.println(encoderUnitsDeciSecToMetersSec(getLeftMaster().getSelectedSensorVelocity()));
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
        System.out.println("o ma boy");
    }

    public void arcadeDrive(final double forwardSpeed, final double rotationSpeed) {
        if (Robot.isReal()) {
            virtualComponents.getDifferentialDrive().arcadeDrive(forwardSpeed * ARCADE_DRIVE_FORWARD_SENSITIVITY,
                    rotationSpeed * ARCADE_DRIVE_ROTATION_SENSITIVITY, false);
        }
        else {
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

    public void resetOdometry(Pose2d pose) {
        virtualComponents.getOdometry().resetPosition(pose,
                Rotation2d.fromDegrees(getHeading()));
    }

    public void tankDriveVolts(double leftVolts, double rightVolts) {
        if (Robot.isReal()) {
            components.getLeftMotors().setVoltage(leftVolts);
            components.getRightMotors().setVoltage(rightVolts);
            virtualComponents.getDifferentialDrive().feed();
        }
        else {
            simulationComponents.getLeftMotors().setVoltage(leftVolts);
            simulationComponents.getRightMotors().setVoltage(rightVolts);
            virtualComponents.getSimDifferentialDrive().feed();
        }
    }

    public double getAverageEncoderDistance() {
        return Robot.isSimulation() ? (getSimLeftMaster().getSelectedSensorPosition() +
                getSimRightMaster().getSelectedSensorPosition()) / 2 : (getLeftMaster().getSelectedSensorPosition() +
                getRightMaster().getSelectedSensorPosition()) / 2;
    }

    public void setMaxOutputArcadeDrive(double maxOutput) {
        if (Robot.isReal()) {
            virtualComponents.getDifferentialDrive().setMaxOutput(maxOutput);
        }
        else {
            virtualComponents.getSimDifferentialDrive().setMaxOutput(maxOutput);
        }
    }

    public void zeroHeading() {
        if (Robot.isReal()) {
            components.getNormelizedPigeonIMU().setYaw(0);
        }
        else {
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
        }
        else {
            virtualComponents.getSimDifferentialDrive().stopMotor();
        }
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

    private void resetEncoders() {
        if (Robot.isReal()) {
            getLeftMaster().setSelectedSensorPosition(0);
            getRightMaster().setSelectedSensorPosition(0);
        }
        else {
            getSimLeftMaster().setSelectedSensorPosition(0);
            getSimRightMaster().setSelectedSensorPosition(0);
        }
    }

    public void resetSimOdometryToPose(Pose2d pose) {//For future Vision integration - will delete comment pre-merge
        resetOdometryToPose(pose);
        getSimRightMaster().getSimCollection().setQuadratureVelocity(0);
        getSimLeftMaster().getSimCollection().setQuadratureVelocity(0);
        simulationComponents.getAnalogGyroSim().setRate(0);
        simulationComponents.getAnalogGyroSim().setAngle(0);
        simulationComponents.getField2d().setRobotPose(pose);
        virtualComponents.getDriveTrainSim().setPose(pose);
        virtualComponents.getDriveTrainSim().setInputs(0, 0);
    }

    public void resetOdometryToPose(Pose2d pose) {
        resetEncoders();
        virtualComponents.getOdometry().resetPosition(pose, pose.getRotation());
        components.getNormelizedPigeonIMU().setYaw(0);
    }

    private void initKaTimer() {
        kaTimer.reset();
        kaTimer.start();
    }

    private void kaPrints() {
        double speed = Robot.isSimulation() ?
            getSimLeftMaster().getSelectedSensorVelocity() : getLeftMaster().getSelectedSensorVelocity();
        double voltage = Robot.isSimulation() ?
            getSimLeftMaster().getMotorOutputVoltage() : getLeftMaster().getMotorOutputVoltage();
        if (speed > 0)
            try {
                writer.println(kaTimer.get() + "," + speed + "," + voltage);
            } catch (Exception e){
                //e.printStackTrace();
            }
    }
}

package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

import java.util.List;
import java.util.function.DoubleSupplier;

import static frc.robot.drivetrain.DriveTrainConstants.*;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.*;

public class DriveTrain extends SubsystemBase {

  private final DriveTrainComponents components;
  private final DriveTrainVirtualComponents vComponents;
  private final SimulationDriveTrainComponents simComponents;

  public DriveTrain(DriveTrainComponents components, DriveTrainVirtualComponents vComponents,
                    SimulationDriveTrainComponents simComponents) {
    this.components = components;
    this.vComponents = vComponents;
    this.simComponents = simComponents;
    Shuffleboard.getTab("DriveTrain").add("Field", simComponents.getField2d());
    SmartDashboard.putData("Field2", simComponents.getField2d());
    simComponents.getField2d().setRobotPose(new Pose2d(1.2, 2.28, Rotation2d.fromDegrees(0)));
    vComponents.getOdometry().resetPosition(new Pose2d(1.2, 2.28, Rotation2d.fromDegrees(0)), Rotation2d.fromDegrees(0));
    resetEncoders();
    if (Robot.isSimulation()) {
      simComponents.getLeftMasterMotor().setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 10);
      simComponents.getRightMasterMotor().setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 10);
    }
  }

  @Override
  public void periodic() {
    vComponents.getOdometry().update(Rotation2d.fromDegrees((Robot.isSimulation() ? getSimOdometryHeading() : getOdometryHeading())),
        (Robot.isSimulation() ? getSimLeftDistance() : getLeftDistance()), (Robot.isSimulation() ? getSimRightDistance() : getRightDistance()));

    simComponents.getField2d().setRobotPose(vComponents.getOdometry().getPoseMeters());
  }

  @Override
  public void simulationPeriodic() {
    vComponents.getDriveTrainSim().setInputs(simComponents.getLeftMasterMotor().
        getMotorOutputPercent() * RobotController.getBatteryVoltage(), simComponents
        .getRightMasterMotor().getMotorOutputPercent() * RobotController.getBatteryVoltage());
    vComponents.getDriveTrainSim().update(0.02);

    simComponents.getLeftMasterMotor().getSimCollection().setQuadratureVelocity(
        (int) metersPerSecToEncoderUnitsPerDeciSecond(vComponents.getDriveTrainSim().getLeftVelocityMetersPerSecond()));
    simComponents.getRightMasterMotor().getSimCollection().setQuadratureVelocity(
        (int) metersPerSecToEncoderUnitsPerDeciSecond(vComponents.getDriveTrainSim().getRightVelocityMetersPerSecond()));
    simComponents.getLeftMasterMotor().getSimCollection().setQuadratureRawPosition(
        (int)metersToEncoderUnits(vComponents.getDriveTrainSim().getLeftPositionMeters()));
    simComponents.getRightMasterMotor().getSimCollection().setQuadratureRawPosition(
        (int)metersToEncoderUnits(vComponents.getDriveTrainSim().getRightPositionMeters()));
    simComponents.getAnalogGyroSim().setAngle(vComponents.getDriveTrainSim().getHeading().getDegrees());
  } //ks = 0.480938416422287
  /* How to find ka:
  * a = (1/ka)*(V - ks - v * kv)
  * Make t and v table
  * calculate a (deltaV / delta T)
  * */

  public double encoderUnitsDeciSecondToMeterSecond(double encoderUnits) {
    return encoderUnitsToMeter(encoderUnits) * 10;
  }

  private void move(DoubleSupplier voltageSupplier) {
    simComponents.getRightMasterMotor().set(voltageSupplier.getAsDouble());
    simComponents.getLeftMasterMotor().set(voltageSupplier.getAsDouble());
  }

  public void initMotionProfileSlot(int slot) {
    components.getLeftMasterMotor().selectProfileSlot(slot, DRIVE_BY_DISTANCE_SLOT);
    components.getRightMasterMotor().selectProfileSlot(slot, DRIVE_BY_DISTANCE_SLOT);
    simComponents.getLeftMasterMotor().selectProfileSlot(slot, DRIVE_BY_DISTANCE_SLOT);
    simComponents.getRightMasterMotor().selectProfileSlot(slot, DRIVE_BY_DISTANCE_SLOT);
  }

  public void arcadeDrive(double forwardSpeed, double rotationSpeed) {
    vComponents.getDifferentialDrive().arcadeDrive(forwardSpeed * ARCADE_DRIVE_FORWARD_SENSITIVITY,
        rotationSpeed * ARCADE_DRIVE_ROTATION_SENSITIVITY, false);
    vComponents.getSimDifferentialDrive().arcadeDrive(forwardSpeed * ARCADE_DRIVE_FORWARD_SENSITIVITY,
        -rotationSpeed * ARCADE_DRIVE_ROTATION_SENSITIVITY, false);
  }

  public void driveByMotionMagic(double leftTarget, double rightTarget) {
    driveMotorByMotionMagic(getLeftMaster(), leftTarget);
    driveMotorByMotionMagic(getRightMaster(), rightTarget);
  }

  public Pose2d getPose() {
    return vComponents.getOdometry().getPoseMeters();
  }

  public DifferentialDriveKinematics getKinematics() {
    return vComponents.getDriveKinematics();
  }

  public SimpleMotorFeedforward getFeedForward() {
    return vComponents.getMotorFeedForward();
  }

  public OnyxTrajectoryGenerator getTrajectoryGenerator() {
    return vComponents.getTrajectoryGenerator();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(encoderUnitsToMeter(getLeftMaster().getSelectedSensorVelocity()
        * 10),
        encoderUnitsToMeter(getRightMaster().getSelectedSensorVelocity() * 10));
  }

  public DifferentialDriveWheelSpeeds getSimWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(encoderUnitsToMeter(getSimLeftMaster().getSelectedSensorVelocity()
        * 10),
        encoderUnitsToMeter(getSimRightMaster().getSelectedSensorVelocity() * 10));
  }

  public boolean isDriveOnTarget(double leftTarget, double rightTarget) {
    return Math.abs(leftTarget - getLeftMaster().getSelectedSensorPosition()) < metersToEncoderUnits(TOLERANCE_METERS) &&
        Math.abs(rightTarget - getRightMaster().getSelectedSensorPosition()) < metersToEncoderUnits(TOLERANCE_METERS);
  }

  public boolean isSimDriveOnTarget(double leftTarget, double rightTarget) {
    return Math.abs(leftTarget - getSimLeftMaster().getSelectedSensorPosition()) < metersToEncoderUnits(TOLERANCE_METERS) &&
        Math.abs(rightTarget - getSimRightMaster().getSelectedSensorPosition()) < metersToEncoderUnits(TOLERANCE_METERS);
  }

  public void driveTrainVelocity(double leftVelocity, double rightVelocity) {
    final double leftFeedForwardVolts = vComponents.getMotorFeedForward().calculate(leftVelocity, 0);
    final double rightFeedForwardVolts = vComponents.getMotorFeedForward().calculate(rightVelocity, 0);

    initMotionProfileSlot(TRAJECTORY_PID_SLOT);
    getLeftMaster().set(ControlMode.Velocity, metersPerSecToEncoderUnitsPerDeciSecond(leftVelocity),
        DemandType.ArbitraryFeedForward, leftFeedForwardVolts / RobotController.getBatteryVoltage());
    getRightMaster().set(ControlMode.Velocity, metersPerSecToEncoderUnitsPerDeciSecond(rightVelocity),
        DemandType.ArbitraryFeedForward, rightFeedForwardVolts / RobotController.getBatteryVoltage());
    getSimLeftMaster().set(ControlMode.Velocity, metersPerSecToEncoderUnitsPerDeciSecond(leftVelocity),
        DemandType.ArbitraryFeedForward, leftFeedForwardVolts / RobotController.getBatteryVoltage());
    getSimRightMaster().set(ControlMode.Velocity, metersPerSecToEncoderUnitsPerDeciSecond(rightVelocity),
        DemandType.ArbitraryFeedForward, rightFeedForwardVolts / RobotController.getBatteryVoltage());
  }

  public void driveTrainVelocityReverse(double leftVelocity, double rightVelocity) {
    driveTrainVelocity(-leftVelocity, -rightVelocity);
  }

  public double getRightTargetFromDistance(double distance) {
    return getTargetFromDistance(getRightMaster(), distance);
  }

  public double getSimRightTargetFromDistance(double distance) {
    return getSimTargetFromDistance(getSimRightMaster(), distance);
  }

  public double getLeftTargetFromDistance(double distance) {
    return getTargetFromDistance(getLeftMaster(), distance);
  }

  public double getSimLeftTargetFromDistance(double distance) {
    return getSimTargetFromDistance(getSimLeftMaster(), distance);
  }

  public void stopDrive() {
    vComponents.getDifferentialDrive().stopMotor();
    vComponents.getSimDifferentialDrive().stopMotor();
  }

  public DriveTrainComponents getComponents() {
    return components;
  }

  public SimulationDriveTrainComponents getVirtualComponents() {
    return simComponents;
  }

  public double getOdometryHeading() {
    return components.getPigeonIMU().getNormalizedYaw();
  }

  public double getSimOdometryHeading() {
    return simComponents.getAnalogGyroSim().getAngle();
  }

  public double getRawRobotHeading() {
    return components.getPigeonIMU().getRawYaw();
  }

  public void setNeutralModeToCoast() {
    components.getLeftMasterMotor().setNeutralMode(NeutralMode.Coast);
    components.getLeftSlaveMotor().setNeutralMode(NeutralMode.Coast);
    components.getRightMasterMotor().setNeutralMode(NeutralMode.Coast);
    components.getRightSlaveMotor().setNeutralMode(NeutralMode.Coast);
    simComponents.getLeftMasterMotor().setNeutralMode(NeutralMode.Coast);
    simComponents.getLeftSlaveMotor().setNeutralMode(NeutralMode.Coast);
    simComponents.getRightMasterMotor().setNeutralMode(NeutralMode.Coast);
    simComponents.getRightSlaveMotor().setNeutralMode(NeutralMode.Coast);
  }

  public void setNeutralModeToBrake() {
    components.getLeftMasterMotor().setNeutralMode(NeutralMode.Brake);
    components.getLeftSlaveMotor().setNeutralMode(NeutralMode.Brake);
    components.getRightMasterMotor().setNeutralMode(NeutralMode.Brake);
    components.getRightSlaveMotor().setNeutralMode(NeutralMode.Brake);
    simComponents.getLeftMasterMotor().setNeutralMode(NeutralMode.Brake);
    simComponents.getLeftSlaveMotor().setNeutralMode(NeutralMode.Brake);
    simComponents.getRightMasterMotor().setNeutralMode(NeutralMode.Brake);
    simComponents.getRightSlaveMotor().setNeutralMode(NeutralMode.Brake);
  }

  public void setGyroAngle(double angle) {
    components.getPigeonIMU().setYaw(angle);
  }

  public void setSimGyroAngle(double angle) {
    simComponents.getAnalogGyroSim().setAngle(angle);
  }

  private void driveMotorByMotionMagic(WPI_TalonFX motor, double target) {
    motor.set(ControlMode.MotionMagic, target, DemandType.ArbitraryFeedForward, ARB_FEED_FORWARD);
  }

  private WPI_TalonFX getLeftMaster() {
    return components.getLeftMasterMotor();
  }

  private WPI_TalonFX getRightMaster() {
    return components.getRightMasterMotor();
  }

  private WPI_TalonSRX getSimLeftMaster() {
    return simComponents.getLeftMasterMotor();
  }

  private WPI_TalonSRX getSimRightMaster() {
    return simComponents.getRightMasterMotor();
  }

  private double getTargetFromDistance(WPI_TalonFX motor, double distance) {
    return metersToEncoderUnits(distance) + motor.getSelectedSensorPosition();
  }

  private double getSimTargetFromDistance(WPI_TalonSRX motor, double distance) {
    return metersToEncoderUnits(distance) + motor.getSelectedSensorPosition();
  }

  private double getLeftDistance() {
    return getLeftMaster().getSelectedSensorPosition(); //TODO: Fix later
  }

  private double getRightDistance() {
    return getRightMaster().getSelectedSensorPosition(); //TODO: Fix later
  }

  private double getSimLeftDistance() {
    return encoderUnitsToMeter(getSimLeftMaster().getSelectedSensorPosition());
  }

  private double getSimRightDistance() {
    return encoderUnitsToMeter(getSimRightMaster().getSelectedSensorPosition());
  }

  private void resetOdometryToPose(Pose2d pose) {//For future Vision integration - will delete comment pre-merge
    resetEncoders();
    vComponents.getOdometry().resetPosition(pose, Rotation2d.fromDegrees(getOdometryHeading()));
  }

  public void resetSimOdometryToPose(Pose2d pose) {//For future Vision integration - will delete comment pre-merge
    resetEncoders();
    vComponents.getOdometry().resetPosition(pose, Rotation2d.fromDegrees(0));
    simComponents.getRightMasterMotor().getSimCollection().setQuadratureVelocity(0);
    simComponents.getLeftMasterMotor().getSimCollection().setQuadratureVelocity(0);
    simComponents.getAnalogGyroSim().setRate(0);
    simComponents.getAnalogGyroSim().setAngle(0);
    simComponents.getField2d().setRobotPose(new Pose2d(1.2, 2.28, Rotation2d.fromDegrees(0)));
    vComponents.getDriveTrainSim().setPose(new Pose2d(1.2, 2.28, Rotation2d.fromDegrees(0)));
    vComponents.getDriveTrainSim().setInputs(0, 0);
  }

  private List<Pose2d> getPoseFromVision() {//For future Vision integration - will delete comment pre-merge
    return List.of(new Pose2d());
  }

  private double metersToEncoderUnits(double meters) {
    return ENCODER_CPR * meters / PERIMETER_METER;
  }

  private double metersPerSecToEncoderUnitsPerDeciSecond(double metersPerSec) {
    return metersToEncoderUnits(metersPerSec / SEC_TO_100MS);
  }

  private double encoderUnitsToMeter(double encoder) {
    return encoder / ENCODER_CPR * PERIMETER_METER;
  }

  private void resetEncoders() {
    getLeftMaster().setSelectedSensorPosition(0);
    getRightMaster().setSelectedSensorPosition(0);
    getSimLeftMaster().setSelectedSensorPosition(0);
    getSimRightMaster().setSelectedSensorPosition(0);
  }
}

package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.List;

import static frc.robot.drivetrain.DriveTrainConstants.*;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.ENCODER_CPR;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.TRAJECTORY_PID_SLOT;

public class DriveTrain extends SubsystemBase {

  private final DriveTrainComponents components;
  private final DriveTrainVirtualComponents vComponents;
  private final SimulationDriveTrainComponents simComponents;

  public DriveTrain(DriveTrainComponents components, DriveTrainVirtualComponents vComponents,
                    SimulationDriveTrainComponents simComponents) {
    this.components = components;
    this.vComponents = vComponents;
    this.simComponents = simComponents;
    resetEncoders();
  }

  @Override
  public void periodic() {
    vComponents.getOdometry().update(Rotation2d.fromDegrees(getOdometryHeading()),
        getLeftDistance() / CM_TO_METERS, getRightDistance() / CM_TO_METERS);
  }

  @Override
  public void simulationPeriodic() {
    simComponents.getDriveTrainSim().setInputs(simComponents.getLeftMasterMotor().
        getMotorOutputPercent() * RobotController.getBatteryVoltage(), simComponents.getRightMasterMotor().
        getMotorOutputPercent() * RobotController.getBatteryVoltage());
    simComponents.getDriveTrainSim().update(0.02);
    simComponents.getLeftMasterMotor().getSimCollection().setQuadratureRawPosition((int)simComponents.
        getDriveTrainSim().getLeftPositionMeters());
    simComponents.getRightMasterMotor().getSimCollection().setQuadratureRawPosition((int)simComponents.
        getDriveTrainSim().getLeftPositionMeters());
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
    simComponents.getDifferentialDrive().arcadeDrive(forwardSpeed * ARCADE_DRIVE_FORWARD_SENSITIVITY,
        rotationSpeed * ARCADE_DRIVE_ROTATION_SENSITIVITY, false);
  }

  public void driveByMotionMagic(double leftTarget, double rightTarget) {
    driveMotorByMotionMagic(getLeftMaster(), leftTarget);
    driveMotorByMotionMagic(getRightMaster(), rightTarget);
  }

  public Pose2d getPose() {
    return vComponents.getOdometry().getPoseMeters();
  }

  public Pose2d getSimPose() {
    return simComponents.getOdometry().getPoseMeters();
  }

  public DifferentialDriveKinematics getKinematics() {
    return vComponents.getDriveKinematics();
  }

  public DifferentialDriveKinematics getSimKinematics() {
    return simComponents.getDriveKinematics();
  }

  public SimpleMotorFeedforward getFeedForward() {
    return vComponents.getMotorFeedForward();
  }

  public SimpleMotorFeedforward getSimFeedForward() {
    return simComponents.getMotorFeedForward();
  }

  public OnyxTrajectoryGenerator getTrajectoryGenerator() {
    return vComponents.getTrajectoryGenerator();
  }

  public OnyxTrajectoryGenerator geSimTrajectoryGenerator() {
    return simComponents.getTrajectoryGenerator();
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
    return Math.abs(leftTarget - getLeftMaster().getSelectedSensorPosition()) < cmToEncoderUnits(TOLERANCE) &&
        Math.abs(rightTarget - getRightMaster().getSelectedSensorPosition()) < cmToEncoderUnits(TOLERANCE);
  }

  public boolean isSimDriveOnTarget(double leftTarget, double rightTarget) {
    return Math.abs(leftTarget - getSimLeftMaster().getSelectedSensorPosition()) < cmToEncoderUnits(TOLERANCE) &&
        Math.abs(rightTarget - getSimRightMaster().getSelectedSensorPosition()) < cmToEncoderUnits(TOLERANCE);
  }

  public void driveTrainVelocity(double leftVelocity, double rightVelocity) {
    final double leftFeedForwardVolts = vComponents.getMotorFeedForward().calculate(leftVelocity, 0);
    final double rightFeedForwardVolts = vComponents.getMotorFeedForward().calculate(rightVelocity, 0);
    final double leftSimFeedForwardVolts = simComponents.getMotorFeedForward().calculate(leftVelocity, 0);
    final double rightSimFeedForwardVolts = simComponents.getMotorFeedForward().calculate(rightVelocity, 0);

    initMotionProfileSlot(TRAJECTORY_PID_SLOT);
    getLeftMaster().set(ControlMode.Velocity, metersPerSecToStepsPer100ms(leftVelocity),
        DemandType.ArbitraryFeedForward, leftFeedForwardVolts / RobotController.getBatteryVoltage());
    getRightMaster().set(ControlMode.Velocity, metersPerSecToStepsPer100ms(rightVelocity),
        DemandType.ArbitraryFeedForward, rightFeedForwardVolts / RobotController.getBatteryVoltage());
  }

  public void driveTrainVelocityReverse(double leftVelocity, double rightVelocity) {
    driveTrainVelocity(-leftVelocity, -rightVelocity);
  }

  public double getRightTargetFromDistance(double distance) {
    return getTargetFromDistance(getRightMaster(), distance);
  }

  public double getLeftTargetFromDistance(double distance) {
    return getTargetFromDistance(getLeftMaster(), distance);
  }

  public void stopDrive() {
    vComponents.getDifferentialDrive().stopMotor();
  }

  public DriveTrainComponents getComponents() {
    return components;
  }

  public double getOdometryHeading() {
    return components.getPigeonIMU().getNormalizedYaw();
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
    return cmToEncoderUnits(distance) + motor.getSelectedSensorPosition();
  }

  private double getLeftDistance() {
    return ((double) getLeftMaster().getSelectedSensorPosition()) / ENCODER_CPR * PERIMETER;
  }

  private double getRightDistance() {
    return ((double) getRightMaster().getSelectedSensorPosition() / ENCODER_CPR) * PERIMETER;
  }

  private void resetOdometryToPose(Pose2d pose) {//For future Vision integration - will delete comment pre-merge
    resetEncoders();
    vComponents.getOdometry().resetPosition(pose, Rotation2d.fromDegrees(getOdometryHeading()));
  }

  private List<Pose2d> getPoseFromVision() {//For future Vision integration - will delete comment pre-merge
    return List.of(new Pose2d());
  }

  private double cmToEncoderUnits(double cm) {
    return CONVERSION_RATE * ENCODER_CPR * cm / PERIMETER;
  }

  private double metersToSteps(double meters) {
    return (ENCODER_CPR / PERIMETER_IN_METERS) * meters;
  }

  private double metersPerSecToStepsPer100ms(double metersPerSec) {
    return metersToSteps(metersPerSec / SEC_TO_100MS);
  }

  private double encoderUnitsToMeter(double encoder) {
    return encoder / ENCODER_CPR * PERIMETER_IN_METERS;
  }

  private void resetEncoders() {
    getLeftMaster().setSelectedSensorPosition(0);
    getRightMaster().setSelectedSensorPosition(0);
  }
}

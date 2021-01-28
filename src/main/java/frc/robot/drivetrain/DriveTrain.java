package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.List;

import static frc.robot.drivetrain.DriveTrainConstants.ARCADE_DRIVE_FORWARD_SENSITIVITY;
import static frc.robot.drivetrain.DriveTrainConstants.ARCADE_DRIVE_ROTATION_SENSITIVITY;
import static frc.robot.drivetrain.DriveTrainConstants.TrajectoryConstants.*;

public class DriveTrain extends SubsystemBase {

  private final DriveTrainComponents components;
  private final SimulationDriveTrainComponents simulationComponents;
  private final DriveTrainVirtualComponents virtualComponents;

  public DriveTrain(DriveTrainComponents components, SimulationDriveTrainComponents simulationComponents,
                    DriveTrainVirtualComponents virtualComponents) {
    this.components = components;
    this.simulationComponents = simulationComponents;
    this.virtualComponents = virtualComponents;
    resetEncoders();
  }

  @Override
  public void periodic() {
    virtualComponents.getOdometry().update(
        Rotation2d.fromDegrees(getHeading()),
        getLeftMaster().getSelectedSensorPosition(),
        getRightMaster().getSelectedSensorPosition());
  }

  public void arcadeDrive(final double forwardSpeed, final double rotationSpeed) {
    virtualComponents.getDifferentialDrive().arcadeDrive(forwardSpeed * ARCADE_DRIVE_FORWARD_SENSITIVITY,
        rotationSpeed * ARCADE_DRIVE_ROTATION_SENSITIVITY, false);
  }

  public Command getAutonomousCommand() {
    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
        new Pose2d(),
        List.of(
            new Translation2d(1, 1),
            new Translation2d(2, -1)
        ),
        new Pose2d(3, 0, Rotation2d.fromDegrees(0)),
        TRAJECTORY_CONFIG
    );

    RamseteCommand ramseteCommand = new RamseteCommand(
        trajectory,
        this::getPose,
        new RamseteController(RAMSETE_B, RAMSETE_ZETA),
        FEEDFORWARD,
        DRIVE_KINEMATICS,
        this::getWheelSpeeds,
        new PIDController(TRAJECTORY_P, 0, 0),
        new PIDController(TRAJECTORY_P, 0, 0),
        this::tankDriveVolts,
        this
    );

    resetOdometry(trajectory.getInitialPose());

    return ramseteCommand.andThen(() -> tankDriveVolts(0, 0));
  }

  public Pose2d getPose() {
    return virtualComponents.getOdometry().getPoseMeters();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftMaster().getSelectedSensorVelocity(),
        getLeftMaster().getSelectedSensorVelocity());
  }

  public void resetOdometry(Pose2d pose) {
    virtualComponents.getOdometry().resetPosition(pose,
        Rotation2d.fromDegrees(getHeading()));
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    components.getLeftMotors().setVoltage(leftVolts);
    components.getRightMotors().setVoltage(rightVolts);
    virtualComponents.getDifferentialDrive().feed();
  }

  public double getAverageEncoderDistance() {
    return (getLeftMaster().getSelectedSensorPosition() +
        getRightMaster().getSelectedSensorPosition()) / 2;
  }

  public void setMaxOutput(double maxOutput) {
    virtualComponents.getDifferentialDrive().setMaxOutput(maxOutput);
  }

  public void zeroHeading() {
    components.getNormelizedPigeonIMU().setYaw(0);
  }

  public double getHeading() {
    return components.getNormelizedPigeonIMU().getNormalizedYaw();
  }

  public double getTurnRate() {
    double[] rawPigeon = new double[3];
    components.getNormelizedPigeonIMU().getRawGyro(rawPigeon);
    return rawPigeon[0];
  }

  public void stopDrive() {
    virtualComponents.getDifferentialDrive().stopMotor();
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

  private WPI_TalonFX getLeftMaster() {
    return components.getLeftMasterMotor();
  }

  private WPI_TalonFX getRightMaster() {
    return components.getRightMasterMotor();
  }

  private void resetEncoders() {
    getLeftMaster().setSelectedSensorPosition(0);
    getRightMaster().setSelectedSensorPosition(0);
  }
}

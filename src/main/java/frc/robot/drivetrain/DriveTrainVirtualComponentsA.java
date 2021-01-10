package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;

public class DriveTrainVirtualComponentsA implements DriveTrainVirtualComponents {
  private final DifferentialDrive differentialDrive;
  private final DifferentialDriveOdometry odometry;
  private final SimpleMotorFeedforward motorFeedforward;
  private final DifferentialDriveKinematics driveKinematics;
  private final DifferentialDriveVoltageConstraint autonomousVoltage;
  private final TrajectoryConfig trajectoryConfig;
  private final OnyxTrajectoryGenerator trajectoryGenerator;

  public DriveTrainVirtualComponentsA(BasicDriveTrainComponentsA componentsA) {
    differentialDrive = new DifferentialDrive(componentsA.getLeftMasterMotor(), componentsA.getRightMasterMotor());
    differentialDrive.setRightSideInverted(false);
    differentialDrive.setSafetyEnabled(false);

    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(0));
    odometry.resetPosition(new Pose2d(), new Rotation2d());

    motorFeedforward = new SimpleMotorFeedforward(VOLTS, VOLT_SECONDS_PER_METER, VOLT_SECONDS_SQUARED_PER_METER);

    driveKinematics = new DifferentialDriveKinematics(TRACKWIDTH_METERS);

    autonomousVoltage = new DifferentialDriveVoltageConstraint(motorFeedforward, driveKinematics, MAX_VOLTAGE);

    trajectoryConfig = new TrajectoryConfig(MAX_SPEED_METERS_PER_SECOND, MAX_ACCELERATION_METERS_PER_SECOND_SQUARED)
        .setKinematics(driveKinematics)
        .addConstraint(autonomousVoltage)
    ;

    trajectoryGenerator = new OnyxTrajectoryGenerator(trajectoryConfig);
  }

  @Override
  public DifferentialDrive getDifferentialDrive() {
    return null;
  }

  @Override
  public DifferentialDriveOdometry getOdometry() {
    return null;
  }

  @Override
  public SimpleMotorFeedforward getMotorFeedForward() {
    return null;
  }

  @Override
  public DifferentialDriveKinematics getDriveKinematics() {
    return null;
  }

  @Override
  public DifferentialDriveVoltageConstraint getAutonomousVoltage() {
    return null;
  }

  @Override
  public TrajectoryConfig getTrajectoryConfig() {
    return null;
  }

  @Override
  public OnyxTrajectoryGenerator getTrajectoryGenerator() {
    return null;
  }
}
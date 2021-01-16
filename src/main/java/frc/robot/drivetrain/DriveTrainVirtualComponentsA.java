package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.system.plant.DCMotor;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpiutil.math.Matrix;

import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.*;

public class DriveTrainVirtualComponentsA implements DriveTrainVirtualComponents {
  private final DifferentialDrive differentialDrive;
  private final DifferentialDriveOdometry odometry;
  private final SimpleMotorFeedforward motorFeedforward;
  private final DifferentialDriveKinematics driveKinematics;
  private final DifferentialDriveVoltageConstraint autonomousVoltage;
  private final TrajectoryConfig trajectoryConfig;
  private final OnyxTrajectoryGenerator trajectoryGenerator;
  private final DifferentialDrivetrainSim driveTrainSim;

  public DriveTrainVirtualComponentsA(DriveTrainComponents componentsA) {
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
        .addConstraint(autonomousVoltage);

    trajectoryGenerator = new OnyxTrajectoryGenerator(trajectoryConfig);

    driveTrainSim = new DifferentialDrivetrainSim(DCMotor.getFalcon500(4), 9.5, 1, 10,
        0.1524, 0.6, null);
  }

  @Override
  public DifferentialDrive getDifferentialDrive() {
    return differentialDrive;
  }

  @Override
  public DifferentialDriveOdometry getOdometry() {
    return odometry;
  }

  @Override
  public SimpleMotorFeedforward getMotorFeedForward() {
    return motorFeedforward;
  }

  @Override
  public DifferentialDriveKinematics getDriveKinematics() {
    return driveKinematics;
  }

  @Override
  public DifferentialDriveVoltageConstraint getAutonomousVoltage() {
    return autonomousVoltage;
  }

  @Override
  public TrajectoryConfig getTrajectoryConfig() {
    return trajectoryConfig;
  }

  @Override
  public OnyxTrajectoryGenerator getTrajectoryGenerator() {
    return trajectoryGenerator;
  }
}

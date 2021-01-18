package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;

public interface DriveTrainVirtualComponents {
  DifferentialDrive getDifferentialDrive();

  DifferentialDrive getSimDifferentialDrive();

  DifferentialDrivetrainSim getDriveTrainSim();

  DifferentialDriveOdometry getOdometry();

  SimpleMotorFeedforward getMotorFeedForward();

  DifferentialDriveKinematics getDriveKinematics();

  DifferentialDriveVoltageConstraint getAutonomousVoltage();

  TrajectoryConfig getTrajectoryConfig();

  OnyxTrajectoryGenerator getTrajectoryGenerator();
}

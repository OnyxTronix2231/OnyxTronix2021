package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.simulation.AnalogGyroSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;

public interface SimulationDriveTrainComponents {
  AnalogGyroSim getAnalogGyroSim();

  WPI_TalonSRX getRightMasterMotor();

  IMotorController getRightSlaveMotor();

  WPI_TalonSRX getLeftMasterMotor();

  IMotorController getLeftSlaveMotor();

  DifferentialDrivetrainSim getDriveTrainSim();

  DifferentialDrive getDifferentialDrive();

  DifferentialDriveOdometry getOdometry();

  SimpleMotorFeedforward getMotorFeedForward();

  DifferentialDriveKinematics getDriveKinematics();

  DifferentialDriveVoltageConstraint getAutonomousVoltage();

  TrajectoryConfig getTrajectoryConfig();

  OnyxTrajectoryGenerator getTrajectoryGenerator();

  Field2d getField2d();
}

package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;

import static frc.robot.drivetrain.DriveTrainConstants.TrajectoryConstants.TRACKWIDTH_METERS;

public class DriveTrainVirtualComponentsA implements DriveTrainVirtualComponents {

  DifferentialDriveKinematics kinematics;
  DifferentialDriveOdometry odometry;

  public DriveTrainVirtualComponentsA() {
    kinematics = new DifferentialDriveKinematics(TRACKWIDTH_METERS);
  }


  @Override
  public DifferentialDriveKinematics getKinematics() {
    return kinematics;
  }

  @Override
  public DifferentialDriveOdometry getOdometry() {
    return odometry;
  }
}

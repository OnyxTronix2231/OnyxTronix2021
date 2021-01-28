package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;

public interface DriveTrainVirtualComponents {

  DifferentialDriveKinematics getKinematics();

  DifferentialDriveOdometry getOdometry();
}

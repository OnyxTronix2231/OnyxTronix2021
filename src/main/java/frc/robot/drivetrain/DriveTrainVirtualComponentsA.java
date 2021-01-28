package frc.robot.drivetrain;

import static frc.robot.drivetrain.DriveTrainConstants.TrajectoryConstants.TRACKWIDTH_METERS;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;

public class DriveTrainVirtualComponentsA implements DriveTrainVirtualComponents {

    DifferentialDriveKinematics kinematics;
    DifferentialDriveOdometry odometry;

    public DriveTrainVirtualComponentsA(){
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

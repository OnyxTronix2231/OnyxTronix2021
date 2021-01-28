package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;

public interface DriveTrainVirtualComponents {

    DifferentialDriveKinematics getKinematics();

    DifferentialDriveOdometry getOdometry();
}

package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;

public interface DriveTrainVirtualComponents {

    DifferentialDrive getDifferentialDrive();

    DifferentialDrive getSimDifferentialDrive();

    DifferentialDrivetrainSim getDriveTrainSim();

    DifferentialDriveOdometry getOdometry();
}

package frc.robot.crossPlatform.pathCommands;

import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.PRIORITY_PATH_A;
import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.PRIORITY_PATH_D;
import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.PRIORITY_PATH_E;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.commands.MoveByPath;
import frc.robot.drivetrain.commands.ResetOdometryToPose;

public class DrivePriority extends SequentialCommandGroup {

    public DrivePriority(DriveTrain driveTrain) {
        super(new ResetOdometryToPose(driveTrain, PRIORITY_PATH_A.getStartPose()),
                new MoveByPath(driveTrain, PRIORITY_PATH_A),
                new MoveByPath(driveTrain, PRIORITY_PATH_D),
               new MoveByPath(driveTrain, PRIORITY_PATH_E));
    }
}

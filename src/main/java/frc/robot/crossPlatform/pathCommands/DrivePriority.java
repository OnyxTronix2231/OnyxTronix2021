package frc.robot.crossPlatform.pathCommands;

import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.PRIORITY_PATH_A;
import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.PRIORITY_PATH_B;
import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.PRIORITY_PATH_C;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.commands.MoveByPath;

public class DrivePriority extends SequentialCommandGroup {

    public DrivePriority(DriveTrain driveTrain) {
        super(new MoveByPath(driveTrain, PRIORITY_PATH_A),
                new MoveByPath(driveTrain, PRIORITY_PATH_B),
               new MoveByPath(driveTrain, PRIORITY_PATH_C));
    }
}

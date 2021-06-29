package frc.robot.crossPlatform.pathCommands;

import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.TONY_HAWK;

import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.commands.MoveByPath;

public class DoTonyHawk extends MoveByPath {
    public DoTonyHawk(DriveTrain driveTrain) {
        super(driveTrain, TONY_HAWK);
    }
}

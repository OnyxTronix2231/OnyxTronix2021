package frc.robot.drivetrain.commands;

import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.TONY_HAWK;

import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.utils.Path;

public class DoTonyHawk extends MoveByPath{
    public DoTonyHawk(DriveTrain driveTrain) {
        super(driveTrain, TONY_HAWK);
    }
}

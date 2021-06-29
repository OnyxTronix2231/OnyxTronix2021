package frc.robot.crossPlatform.pathCommands;

import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.ONE_METER_BACKWARDS;

import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.commands.MoveByPath;

public class OneMeterReversed extends MoveByPath {
    public OneMeterReversed(DriveTrain driveTrain) {
        super(driveTrain, ONE_METER_BACKWARDS);
    }
}

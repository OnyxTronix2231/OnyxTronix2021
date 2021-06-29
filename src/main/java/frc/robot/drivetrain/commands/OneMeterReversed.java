package frc.robot.drivetrain.commands;

import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.ONE_METER_BACKWARDS;

import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.utils.Path;

public class OneMeterReversed extends MoveByPath{
    public OneMeterReversed(DriveTrain driveTrain) {
        super(driveTrain, ONE_METER_BACKWARDS);
    }
}

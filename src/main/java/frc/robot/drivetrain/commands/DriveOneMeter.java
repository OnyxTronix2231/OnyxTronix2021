package frc.robot.drivetrain.commands;

import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.ONE_METER_FORWARD;

import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.utils.Path;

public class DriveOneMeter extends MoveByPath {
    public DriveOneMeter(DriveTrain driveTrain) {
        super(driveTrain, ONE_METER_FORWARD);
    }
}

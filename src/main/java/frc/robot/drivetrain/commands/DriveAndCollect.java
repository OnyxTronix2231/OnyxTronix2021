package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.collector.Collector;
import frc.robot.collector.commands.OpenAndCollect;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.utils.Path;

import static frc.robot.collector.CollectorConstants.BallCollectorConstantsA.COLLECTION_SPEED;

public class DriveAndCollect extends ParallelCommandGroup {
    public DriveAndCollect(DriveTrain driveTrain, Collector collector, Path path) {
        super(new OpenAndCollect(collector, () -> COLLECTION_SPEED), new MoveByPath(driveTrain, path));
    }
}

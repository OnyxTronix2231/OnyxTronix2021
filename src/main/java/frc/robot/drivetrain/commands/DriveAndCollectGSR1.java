package frc.robot.drivetrain.commands;

import static frc.robot.drivetrain.skills.SkillsConstants.Paths.GALACTIC_SEARCH_BLUE_FIRST;
import static frc.robot.drivetrain.skills.SkillsConstants.Paths.GALACTIC_SEARCH_RED_FIRST;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.collector.Collector;
import frc.robot.collector.commands.OpenAndCollect;
import frc.robot.drivetrain.DriveTrain;

public class DriveAndCollectGSR1 extends ParallelCommandGroup {

    public DriveAndCollectGSR1(DriveTrain driveTrain, Collector collector) {
        super(new OpenAndCollect(collector, () -> 0.5), new MoveByPath(driveTrain, GALACTIC_SEARCH_RED_FIRST));
    }
}

package frc.robot.drivetrain.commands;

import static frc.robot.drivetrain.skills.SkillsConstants.Paths.GALACTIC_SEARCH_BLUE_FIRST;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.collector.Collector;
import frc.robot.collector.commands.OpenAndCollect;
import frc.robot.collector.commands.OpenCollectorPistons;
import frc.robot.drivetrain.DriveTrain;

public class DriveAndCollectGSB1 extends ParallelCommandGroup {

    public DriveAndCollectGSB1(DriveTrain driveTrain, Collector collector) {
        super(new OpenAndCollect(collector, () -> 0.8), new SequentialCommandGroup(new WaitCommand(0), new MoveByPath(driveTrain, GALACTIC_SEARCH_BLUE_FIRST)));
    }
}

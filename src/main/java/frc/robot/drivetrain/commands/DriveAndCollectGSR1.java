package frc.robot.drivetrain.commands;

import static frc.robot.drivetrain.skills.SkillsConstants.Paths.GALACTIC_SEARCH_BLUE_FIRST;
import static frc.robot.drivetrain.skills.SkillsConstants.Paths.GALACTIC_SEARCH_RED_FIRST;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.collector.Collector;
import frc.robot.collector.commands.CloseCollectorPistons;
import frc.robot.collector.commands.OpenAndCollect;
import frc.robot.drivetrain.DriveTrain;

public class DriveAndCollectGSR1 extends ParallelCommandGroup {

    private final Collector collector;

    public DriveAndCollectGSR1(DriveTrain driveTrain, Collector collector) {
        super(new OpenAndCollect(collector, () -> -0.7), new MoveByPath(driveTrain, GALACTIC_SEARCH_RED_FIRST));
        this.collector = collector;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        collector.closePistons();
    }
}

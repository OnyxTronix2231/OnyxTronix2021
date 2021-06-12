package frc.robot.arc.commands;

import static frc.robot.arc.ArcConstants.MIN_POSSIBLE_ANGLE;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.arc.Arc;

public class ActivateCommandAndReturnToStart extends ParallelCommandGroup {

    private final Arc arc;

    public ActivateCommandAndReturnToStart(Arc arc, CommandBase command) {
        super(command);
        this.arc = arc;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        CommandScheduler.getInstance().schedule(new MoveArcToAngle(arc, () -> MIN_POSSIBLE_ANGLE));
    }
}

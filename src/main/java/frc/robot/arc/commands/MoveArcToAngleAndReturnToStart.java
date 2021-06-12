package frc.robot.arc.commands;

import static frc.robot.arc.ArcConstants.MIN_POSSIBLE_ANGLE;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.arc.Arc;

import java.util.function.DoubleSupplier;

public class MoveArcToAngleAndReturnToStart extends MoveArcToAngle {

    private final Arc arc;

    public MoveArcToAngleAndReturnToStart(Arc arc, DoubleSupplier angleSupplier) {
        super(arc, angleSupplier);
        this.arc = arc;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        CommandScheduler.getInstance().schedule(new MoveArcToAngle(arc, () -> MIN_POSSIBLE_ANGLE));
    }
}

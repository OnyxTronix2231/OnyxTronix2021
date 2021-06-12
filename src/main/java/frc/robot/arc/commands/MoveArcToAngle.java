package frc.robot.arc.commands;

import static frc.robot.arc.ArcConstants.MIN_POSSIBLE_ANGLE;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.arc.Arc;

import java.util.function.DoubleSupplier;

class MoveArcToAngle extends CommandBase {

    private final Arc arc;
    private final DoubleSupplier angleSupplier;

    protected MoveArcToAngle(Arc arc, DoubleSupplier angleSupplier) {
        this.angleSupplier = angleSupplier;
        this.arc = arc;
        addRequirements(arc);
    }

    @Override
    public void initialize() {
        arc.initMoveToAngle(angleSupplier.getAsDouble());
    }

    @Override
    public void execute() {
        arc.updateMoveToAngle(angleSupplier.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return arc.isOnTarget();
    }

    @Override
    public void end(boolean interrupted) {
        CommandScheduler.getInstance().schedule(new MoveArcToAngle(arc, () -> MIN_POSSIBLE_ANGLE));
        arc.stop();
    }
}

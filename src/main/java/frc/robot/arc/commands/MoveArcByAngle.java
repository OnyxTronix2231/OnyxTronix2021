package frc.robot.arc.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.arc.Arc;

import java.util.function.DoubleSupplier;

public class MoveArcByAngle extends CommandBase {

    private final Arc arc;
    private final DoubleSupplier angleSupplier;

    public MoveArcByAngle(Arc arc, DoubleSupplier angleSupplier) {
        this.angleSupplier = angleSupplier;
        this.arc = arc;
        addRequirements(arc);
    }

    @Override
    public void initialize() {
        arc.initMoveArcToAngle(angleSupplier.getAsDouble());
    }

    @Override
    public void execute() {
        arc.updateMoveArcToAngle(angleSupplier.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return arc.isOnTarget();
    }

    @Override
    public void end(boolean interrupted) {
        arc.stopArc();
    }
}

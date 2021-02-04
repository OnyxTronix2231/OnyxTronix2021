package frc.robot.arc.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.arc.Arc;

import java.util.function.DoubleSupplier;

public class MoveToAngle extends CommandBase {

    private final Arc arc;
    private final DoubleSupplier angleSupplier;

    public MoveToAngle(Arc arc, DoubleSupplier angleSupplier) {
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
        arc.stop();
    }
}

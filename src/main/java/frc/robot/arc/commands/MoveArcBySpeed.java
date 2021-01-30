package frc.robot.arc.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.arc.Arc;

import java.util.function.DoubleSupplier;

public class MoveArcBySpeed extends CommandBase {

    protected final Arc arc;
    private final DoubleSupplier speedSupplier;

    public MoveArcBySpeed(Arc arc, DoubleSupplier speedSupplier) {
        this.speedSupplier = speedSupplier;
        this.arc = arc;
        addRequirements(arc);
    }

    @Override
    public void execute() {
        arc.moveArcBySpeed(speedSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        arc.stop();
    }
}

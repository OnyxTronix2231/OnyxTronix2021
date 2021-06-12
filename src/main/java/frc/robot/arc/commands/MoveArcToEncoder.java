package frc.robot.arc.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.arc.Arc;

import java.util.function.DoubleSupplier;

public class MoveArcToEncoder extends CommandBase {

    private final Arc arc;
    private final DoubleSupplier encoderUnitsTarget;

    public MoveArcToEncoder(Arc arc, DoubleSupplier encoderUnitsTarget){
        this.arc = arc;
        this.encoderUnitsTarget = encoderUnitsTarget;
    }

    @Override
    public void initialize() {
        arc.initMoveToEncoder(encoderUnitsTarget.getAsDouble());
    }

    @Override
    public void execute() {
        arc.updateMoveToEncoder(encoderUnitsTarget.getAsDouble());
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

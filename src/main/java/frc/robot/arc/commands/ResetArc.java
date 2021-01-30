package frc.robot.arc.commands;

import frc.robot.arc.Arc;

import java.util.function.DoubleSupplier;

public class ResetArc extends MoveArcBySpeed {

    public ResetArc(Arc arc, DoubleSupplier resetSpeed){
        super(arc, resetSpeed);
    }

    @Override
    public boolean isFinished() {
        return arc.limitReverse();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        arc.resetEncoder();
    }
}
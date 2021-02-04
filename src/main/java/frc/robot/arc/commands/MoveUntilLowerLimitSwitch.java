package frc.robot.arc.commands;

import frc.robot.arc.Arc;

import java.util.function.DoubleSupplier;

public class MoveUntilLowerLimitSwitch extends MoveBySpeed {

    public MoveUntilLowerLimitSwitch(Arc arc, DoubleSupplier resetSpeed){
        super(arc, resetSpeed);
    }

    @Override
    public boolean isFinished() {
        return arc.hasHitLowerLimit();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        arc.resetEncoder();
    }
}

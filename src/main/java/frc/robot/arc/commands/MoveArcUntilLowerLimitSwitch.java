package frc.robot.arc.commands;

import static frc.robot.arc.ArcConstants.MOVE_ARC_TO_SWITCH_LIMIT_SPEED;

import frc.robot.arc.Arc;

import java.beans.Encoder;

public class MoveArcUntilLowerLimitSwitch extends MoveArcBySpeed {

    public MoveArcUntilLowerLimitSwitch(Arc arc) {
        super(arc, () -> MOVE_ARC_TO_SWITCH_LIMIT_SPEED);
    }

    @Override
    public boolean isFinished() {
        return arc.hasHitReverseLimit();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        arc.resetEncoder();
    }
}

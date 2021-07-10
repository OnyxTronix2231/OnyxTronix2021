package frc.robot.arc.commands;

import frc.robot.arc.Arc;

import static frc.robot.arc.ArcConstants.MOVE_ARC_TO_SWITCH_LIMIT_SPEED;

public class MoveArcUntilLowerLimitSwitch extends MoveArcBySpeed {

    public MoveArcUntilLowerLimitSwitch(Arc arc) {
        super(arc, () -> MOVE_ARC_TO_SWITCH_LIMIT_SPEED);
    }

    @Override
    public void initialize() {
        arc.configReverseSoftLimitEnable(false);
    }

    @Override
    public boolean isFinished() {
        return arc.hasHitReverseLimit();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        arc.resetEncoderByAbsoluteValue();
        arc.configReverseSoftLimitEnable(true);
    }
}

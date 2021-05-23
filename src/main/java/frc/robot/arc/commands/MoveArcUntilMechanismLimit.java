package frc.robot.arc.commands;

import static frc.robot.arc.ArcConstants.MOVE_ARC_TO_MECHANISM_LIMIT_SPEED;

import frc.robot.arc.Arc;

public class MoveArcUntilMechanismLimit extends MoveArcBySpeed {

    public MoveArcUntilMechanismLimit(Arc arc) {
        super(arc, () -> MOVE_ARC_TO_MECHANISM_LIMIT_SPEED);
    }

    @Override
    public void initialize() {
        arc.disableLimitSwitches();
    }

    @Override
    public boolean isFinished() {
        return arc.isMoving();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        arc.resetEncoder();
        arc.enableLimitSwitches();
    }
}

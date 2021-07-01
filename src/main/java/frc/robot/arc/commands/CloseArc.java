package frc.robot.arc.commands;

import static frc.robot.arc.ArcConstants.MIN_POSSIBLE_ANGLE;

import frc.robot.arc.Arc;

public class CloseArc extends MoveArcToAngle {

    private Arc arc;

    public CloseArc(Arc arc) {
        super(arc, ()-> MIN_POSSIBLE_ANGLE);
    }

    @Override
    public boolean isFinished() {
        return arc.isOnTarget();
    }
}

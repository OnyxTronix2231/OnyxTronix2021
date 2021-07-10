package frc.robot.arc.commands;

import frc.robot.arc.Arc;

import static frc.robot.arc.ArcConstants.MIN_POSSIBLE_ANGLE;

public class CloseArc extends MoveArcToAngle {
    public CloseArc(Arc arc) {
        super(arc, () -> MIN_POSSIBLE_ANGLE);
    }
}

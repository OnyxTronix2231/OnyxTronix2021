package frc.robot.arc.commands;

import frc.robot.arc.Arc;
import frc.robot.vision.visionMainChallenge.Vision;

public class MoveArcByVision extends MoveArcToDistance {

    public MoveArcByVision(Arc arc, Vision vision) {
        super(arc, () -> vision.getChosenTarget().getAirDistanceTurretToTarget());
    }
}

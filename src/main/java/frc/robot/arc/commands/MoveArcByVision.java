package frc.robot.arc.commands;

import frc.robot.arc.Arc;
import frc.robot.vision.commands.ActByVision;
import frc.robot.vision.visionMainChallenge.Vision;

public class MoveArcByVision extends ActByVision {

    public MoveArcByVision(Arc arc, Vision vision) {
        super(new MoveArcByDistance(arc, () -> vision.getChosenTarget().getAirDistanceTurretToTarget()), vision);
    }
}

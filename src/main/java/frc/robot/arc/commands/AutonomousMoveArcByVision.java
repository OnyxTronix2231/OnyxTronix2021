package frc.robot.arc.commands;

import frc.robot.arc.Arc;
import frc.robot.vision.commands.ActByVision;
import frc.robot.vision.visionMainChallenge.Vision;

public class AutonomousMoveArcByVision extends ActByVision {

    public AutonomousMoveArcByVision(Arc arc, Vision vision) {
        super(new AutonomousMoveArcByDistance(arc, () -> vision.getOuterTarget().getAirDistanceTurretToTarget()), vision);
    }
}

package frc.robot.arc.commands;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.vision.commands.ActByVision;
import frc.robot.vision.visionMainChallenge.Vision;

public class MoveArcByVision extends ActByVision {

    public MoveArcByVision(Arc arc, Trigger closeArc, Vision vision) {
        super(new MoveArcByDistance(arc, closeArc, () -> vision.getOuterTarget().getAirDistanceTurretToTarget()), vision);
    }
}

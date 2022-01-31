package frc.robot.arc.commands;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.vision.commands.ActByVision;
import frc.robot.vision.visionMainChallenge.Vision;

public class MoveArcByVision extends ActByVision {

    public MoveArcByVision(Arc arc, Trigger closeArc, Vision vision) {
        super(new MoveArcByDistance(arc, closeArc, () -> vision.getChosenTarget() == null ? 0 : vision.getChosenTarget()
                .getAirDistanceTurretToTarget()), vision);
    }

    public MoveArcByVision(Arc arc, Vision vision) {
        super(new MoveArcByDistance(arc, () -> vision.getChosenTarget() == null ? 0 : vision.getChosenTarget()
                .getAirDistanceTurretToTarget()), vision);
    }

    @Override
    public boolean isFinished() {
        if (super.isFinished()) {
            initialize();
        }
        return false;
    }
}

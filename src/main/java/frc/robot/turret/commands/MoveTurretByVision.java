package frc.robot.turret.commands;

import frc.robot.turret.Turret;
import frc.robot.vision.visionMainChallenge.Vision;

public class MoveTurretByVision extends MoveTurretByAngleAndKeep{

    public MoveTurretByVision(Turret turret, Vision vision) {
        super(turret, () -> vision.getChosenTarget() == null ? 0 :
                vision.getChosenTarget().getHorizontalAngleTargetToTurret());
    }
}

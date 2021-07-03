package frc.robot.turret.commands;

import frc.robot.turret.Turret;
import frc.robot.vision.commands.ActByVision;
import frc.robot.vision.visionMainChallenge.Vision;

public class MoveTurretByVision extends ActByVision {

    public MoveTurretByVision(Turret turret, Vision vision) {
        super(
            new MoveTurretByAngleAndKeep(turret, () ->  vision.getChosenTarget().getHorizontalAngleTargetToTurret()),
            vision
        );
    }
}

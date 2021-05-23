package frc.robot.shooter.commands;

import frc.robot.shooter.Shooter;
import frc.robot.vision.commands.ActByVision;
import frc.robot.vision.visionMainChallenge.Vision;

public class SpinShooterByVision extends ActByVision {

    public SpinShooterByVision(Shooter shooter, Vision vision) {
        super(new SpinShooterByDistance(shooter, () -> vision.getChosenTarget().getAirDistanceTurretToTarget()), vision);
    }
}

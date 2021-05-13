package frc.robot.shooter.commands;

import frc.robot.shooter.Shooter;
import frc.robot.vision.visionMainChallenge.Vision;

public class SpinShooterByVision extends SpinShooterByDistance {

    public SpinShooterByVision(Shooter shooter, Vision vision) {
        super(shooter, () -> vision.getChosenTarget().getAirDistanceTurretToTarget());
    }
}

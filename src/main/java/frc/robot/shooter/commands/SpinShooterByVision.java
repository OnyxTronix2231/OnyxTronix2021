package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.shooter.Shooter;
import frc.robot.vision.visionMainChallenge.Vision;

public class SpinShooterByVision extends ConditionalCommand {

  public SpinShooterByVision(Shooter shooter, Vision vision) {
    super(new SpinShooterByDistance(shooter, () -> vision.getChosenTarget().getAirDistanceTurretToTarget()),
        new InstantCommand(), vision::hasTarget);
  }
}

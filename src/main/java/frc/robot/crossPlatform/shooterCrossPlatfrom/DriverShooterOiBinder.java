package frc.robot.crossPlatform.shooterCrossPlatfrom;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.CloseBallTriggerPiston;
import frc.robot.revolver.Revolver;
import frc.robot.shooter.Shooter;
import frc.robot.vision.visionMainChallenge.Vision;

public class DriverShooterOiBinder {

    public DriverShooterOiBinder(Shooter shooter, Arc arc, Vision vision, BallTrigger ballTrigger, Revolver revolver,
                                 Trigger shootBallTrigger) {
        shootBallTrigger.whileActiveContinuous(new ShootBall(shooter, ballTrigger, arc, revolver,
                () -> vision.getChosenTarget().getAirDistanceTurretToTarget(),
                () -> ShooterCrossPlatformConstanns.ShooterCrossPlatformConstannsA.BALLTRIGER_SPEED,
                () -> ShooterCrossPlatformConstanns.ShooterCrossPlatformConstannsA.REVOLVER_RPM_WHILE_SHOOTING))
                .whenInactive(new CloseBallTriggerPiston(ballTrigger));
    }
}

package frc.robot.crossPlatform.shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.arc.Arc;
import frc.robot.arc.commands.MoveArcToDistance;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.OpenBallTriggerPiston;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.SpinShooterByDistance;
import frc.robot.shooter.commands.WaitUntilShooterOnTarget;
import frc.robot.vision.visionMainChallenge.Vision;

public class ShootBall extends SequentialCommandGroup {

    public ShootBall(Shooter shooter, Vision vision, BallTrigger ballTrigger, Arc arc) {
        super(new MoveArcToDistance(arc, () -> vision.getChosenTarget().getAirDistanceTurretToTarget()),
                new SpinShooterByDistance(shooter, () -> vision.getChosenTarget().getAirDistanceTurretToTarget()),
                new WaitUntilShooterOnTarget(shooter),
                new OpenBallTriggerPiston(ballTrigger));
    }
}

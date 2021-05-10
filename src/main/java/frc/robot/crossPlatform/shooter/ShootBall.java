package frc.robot.crossPlatform.shooter;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.arc.Arc;
import frc.robot.arc.commands.MoveArcToDistance;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.crossPlatform.conveyor.SpinRevolverAndTriggerThenOpenTriggerPiston;
import frc.robot.revolver.Revolver;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.SpinShooterByDistance;
import frc.robot.vision.visionMainChallenge.Vision;

public class ShootBall extends ParallelCommandGroup {

    public ShootBall(Shooter shooter, Vision vision, BallTrigger ballTrigger, Arc arc, Revolver revolver) {
        super(new SpinShooterByDistance(shooter, () -> vision.getChosenTarget().getAirDistanceTurretToTarget()),
                new MoveArcToDistance(arc, () -> vision.getChosenTarget().getAirDistanceTurretToTarget()),
                (new WaitUntilCommand(shooter::isOnTarget).alongWith(
                        new WaitUntilCommand(arc::isOnTarget))).andThen(
                        new SpinRevolverAndTriggerThenOpenTriggerPiston(revolver, ballTrigger, () -> 1, () -> 1)));
    }
}

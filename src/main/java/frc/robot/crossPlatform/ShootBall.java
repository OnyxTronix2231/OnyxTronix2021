package frc.robot.crossPlatform;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.arc.Arc;
import frc.robot.arc.commands.MoveArcByVision;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.ControlBallTriggerByConditions;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.SpinShooterByVision;
import frc.robot.turret.Turret;
import frc.robot.turret.commands.MoveTurretByVision;
import frc.robot.vision.visionMainChallenge.Vision;

import java.util.function.DoubleSupplier;

public class ShootBall extends ParallelCommandGroup {

    private final BallTrigger ballTrigger;

    public ShootBall(Shooter shooter, BallTrigger ballTrigger, Arc arc,
                     Turret turret, Vision vision, DoubleSupplier ballTriggerSpeedSupplier) {
        super(
                new SpinShooterByVision(shooter, vision),
                new MoveArcByVision(arc, vision),
                new MoveTurretByVision(turret, vision),
                new ControlBallTriggerByConditions(ballTrigger, ballTriggerSpeedSupplier,
                        shooter::isOnTarget, arc::isOnTarget, turret::isOnTarget));
        this.ballTrigger = ballTrigger;
    }
}

package frc.robot.crossPlatform;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.arc.commands.MoveArcAndCloseByTrigger;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.ControlBallTriggerByConditions;
import frc.robot.ballTrigger.commands.SpinBallTriggerByRPM;
import frc.robot.ballTrigger.commands.SpinBallTriggerBySpeed;
import frc.robot.revolver.Revolver;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.SpinShooterByRPM;
import frc.robot.turret.Turret;
import frc.robot.vision.visionMainChallenge.Vision;

import java.util.function.DoubleSupplier;

public class ShootBall extends ParallelCommandGroup {

    private final BallTrigger ballTrigger;

    public ShootBall(Shooter shooter, BallTrigger ballTrigger, Arc arc,
                     Turret turret, Vision vision, Revolver revolver, DoubleSupplier ballTriggerRPMSupplier,
                     Trigger shootBall) {
        super(
                //new SpinShooterByVision(shooter, vision),
                //new MoveArcByVision(arc, shootBall, vision),
                //new MoveTurretByVision(turret, vision),
                new SpinShooterByRPM(shooter, shooter::getRpm),
                new SpinBallTriggerByRPM(ballTrigger, ballTriggerRPMSupplier),
                new MoveArcAndCloseByTrigger(arc, shootBall, arc::getTestAngle),
                new ControlBallTriggerByConditions(ballTrigger, shooter::isOnTarget,
                        revolver::isOnTarget, ballTrigger::isOnTarget, arc::isOnTarget, ()-> true));
        this.ballTrigger = ballTrigger;
    }
}

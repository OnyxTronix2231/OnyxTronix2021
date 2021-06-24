package frc.robot.crossPlatform;

import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_RPM_WHILE_SHOOTING;
import static frc.robot.crossPlatform.CrossPlatformConstants.TriggerConstantsA.BALL_TRIGGER_RPM;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.arc.commands.MoveArcAndCloseByTrigger;
import frc.robot.arc.commands.MoveArcByVision;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.ControlBallTriggerByConditions;
import frc.robot.ballTrigger.commands.SpinBallTriggerByRPM;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.SpinRevolverByRPM;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.SpinShooterByRPM;
import frc.robot.shooter.commands.SpinShooterByVision;
import frc.robot.turret.Turret;
import frc.robot.turret.commands.MoveTurretByVision;
import frc.robot.vision.visionMainChallenge.Vision;

public class ShootBall extends ParallelCommandGroup {

    private final BallTrigger ballTrigger;

    public ShootBall(Shooter shooter, BallTrigger ballTrigger, Arc arc,
                     Turret turret, Vision vision, Revolver revolver, Trigger shootBall) {
        super(
                //new SpinShooterByVision(shooter, vision),
                //new MoveArcByVision(arc, shootBall, vision),
                //new MoveTurretByVision(turret, vision),
                new SpinBallTriggerByRPM(ballTrigger, () -> BALL_TRIGGER_RPM),
                new SpinShooterByRPM(shooter ,shooter::getRpm),
                new SpinRevolverByRPM(revolver, () -> REVOLVER_RPM_WHILE_SHOOTING),
                new MoveArcAndCloseByTrigger(arc, shootBall, arc::getTestAngle),
                new ControlBallTriggerByConditions(ballTrigger, shooter::isOnTarget, revolver::isOnTarget,
                        ballTrigger::isOnTarget ,arc::isOnTarget /*turret::isOnTarget*/ ));
        this.ballTrigger = ballTrigger;
    }
}

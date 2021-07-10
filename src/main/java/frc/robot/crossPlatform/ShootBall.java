package frc.robot.crossPlatform;

import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_RPM_WHILE_SHOOTING;
import static frc.robot.crossPlatform.CrossPlatformConstants.TriggerConstantsA.BALL_TRIGGER_RPM;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.arc.commands.CloseArc;
import frc.robot.arc.commands.MoveArcByVision;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.ControlBallTriggerByConditions;
import frc.robot.ballTrigger.commands.SpinBallTriggerByRPM;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.SpinRevolverByRPM;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.SpinShooterByVision;
import frc.robot.turret.commands.MoveTurretByVision;
import frc.robot.vision.visionMainChallenge.Vision;
import frc.robot.yawControll.YawControl;
import frc.robot.yawControll.commands.SmartMoveTurretToTargetArea;

public class ShootBall extends ParallelCommandGroup {

    public ShootBall(Shooter shooter, BallTrigger ballTrigger, Arc arc,
                     YawControl yawControl, Vision vision, Revolver revolver, Trigger shootBall) {
        super(
                new SpinBallTriggerByRPM(ballTrigger, () -> BALL_TRIGGER_RPM),
                new SpinRevolverByRPM(revolver, () -> REVOLVER_RPM_WHILE_SHOOTING),
                new SmartMoveTurretToTargetArea(yawControl, vision).andThen(
                        new MoveTurretByVision(yawControl, vision).alongWith(
                        new MoveArcByVision(arc,vision),
                        new SpinShooterByVision(shooter, vision))),
                new ControlBallTriggerByConditions(ballTrigger, shooter::isOnTarget, revolver::isOnTarget,
                        arc::isOnTarget, yawControl::isOnTarget));
        shootBall.whenInactive(new CloseArc(arc));
    }
}

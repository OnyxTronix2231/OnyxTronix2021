package frc.robot.crossPlatform;

import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_RPM_WHILE_SHOOTING;
import static frc.robot.crossPlatform.CrossPlatformConstants.TriggerConstantsA.BALL_TRIGGER_RPM;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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
import frc.robot.vision.visionMainChallenge.Vision;
import frc.robot.yawControll.YawControl;
import frc.robot.yawControll.commands.MoveTurretToTargetArea;
import frc.robot.yawControll.commands.SmartMoveTurretToTargetArea;

public class ShootBall extends ParallelCommandGroup {

    public ShootBall(Shooter shooter, BallTrigger ballTrigger, Arc arc,
                     YawControl yawControl, Vision vision, Revolver revolver, Trigger shootBall) {
        super(
                new SpinBallTriggerByRPM(ballTrigger, () -> BALL_TRIGGER_RPM),
                new SpinRevolverByRPM(revolver, () -> REVOLVER_RPM_WHILE_SHOOTING),
                new SequentialCommandGroup(new MoveTurretToTargetArea(yawControl),
                        new SmartMoveTurretToTargetArea(yawControl, vision)),
                new MoveArcByVision(arc,shootBall ,vision),
                new SpinShooterByVision(shooter, vision),
                new ControlBallTriggerByConditions(ballTrigger, shooter::isOnTarget, revolver::isOnTarget,
                        ballTrigger::isOnTarget, arc::isOnTarget, yawControl::isOnTarget,
                        revolver::isHallEffectOnTarget, ()-> vision.getChosenTarget() != null));
        shootBall.whenInactive(new CloseArc(arc));
    }
}

package frc.robot.crossPlatform;

import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_RPM_WHILE_SHOOTING;
import static frc.robot.crossPlatform.CrossPlatformConstants.TriggerConstantsA.BALL_TRIGGER_RPM;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.arc.commands.CloseArc;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.ControlBallTriggerByConditions;
import frc.robot.ballTrigger.commands.SpinBallTriggerByRPM;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.SpinRevolverByRPM;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.SpinShooterByRPM;
import frc.robot.yawControll.YawControl;

public class ShootBall extends ParallelCommandGroup {

    public ShootBall(Shooter shooter, BallTrigger ballTrigger, Arc arc,
                     YawControl yawControl, Revolver revolver, Trigger shootBall, ShootWhileDrivingCalc shootWhileDrivingCalc) {
        super(
                new SpinBallTriggerByRPM(ballTrigger, () -> BALL_TRIGGER_RPM),
                new SpinRevolverByRPM(revolver, () -> REVOLVER_RPM_WHILE_SHOOTING),
                new SequentialCommandGroup(
                new SpinShooterByRPM(shooter, ()-> shooter.getSpeedShooterRPM(shootWhileDrivingCalc)),
                new ControlBallTriggerByConditions(ballTrigger, shooter::isOnTarget, revolver::isOnTarget,
                        ballTrigger::isOnTarget, arc::isOnTarget, yawControl::isOnTarget,
                        revolver::isHallEffectOnTarget)));
        shootBall.whenInactive(new CloseArc(arc));
    }
}

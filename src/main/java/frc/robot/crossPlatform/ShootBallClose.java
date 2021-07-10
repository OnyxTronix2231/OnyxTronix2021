package frc.robot.crossPlatform;

import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_RPM_WHILE_SHOOTING;
import static frc.robot.crossPlatform.CrossPlatformConstants.ShooterConstantA.CLOSE_SHOOTER_RPM;
import static frc.robot.crossPlatform.CrossPlatformConstants.TriggerConstantsA.BALL_TRIGGER_RPM;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.ControlBallTriggerByConditions;
import frc.robot.ballTrigger.commands.SpinBallTriggerByRPM;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.SpinRevolverByRPM;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.SpinShooterByRPM;
import frc.robot.turret.commands.MoveTurretToAngleAndKeep;
import frc.robot.yawControll.YawControl;

public class ShootBallClose extends ParallelCommandGroup {

    public ShootBallClose(Shooter shooter, YawControl yawControl, BallTrigger ballTrigger, Revolver revolver) {
        super(
                new MoveTurretToAngleAndKeep(yawControl, () -> 0),
                new SpinBallTriggerByRPM(ballTrigger, () -> BALL_TRIGGER_RPM),
                new SpinShooterByRPM(shooter, () -> CLOSE_SHOOTER_RPM),
                new SpinRevolverByRPM(revolver, () -> REVOLVER_RPM_WHILE_SHOOTING),
                new ControlBallTriggerByConditions(ballTrigger, shooter::isOnTarget, revolver::isOnTarget,
                        ballTrigger::isOnTarget, revolver::isHallEffectOnTarget, yawControl::isOnTarget));
    }
}

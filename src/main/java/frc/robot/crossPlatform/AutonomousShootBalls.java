package frc.robot.crossPlatform;

import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_RPM_WHILE_SHOOTING;
import static frc.robot.crossPlatform.CrossPlatformConstants.TriggerConstantsA.BALL_TRIGGER_RPM;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.arc.Arc;
import frc.robot.arc.commands.AutonomousMoveArcByVision;
import frc.robot.arc.commands.CloseArc;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.ControlBallTriggerByConditions;
import frc.robot.ballTrigger.commands.SpinBallTriggerByRPM;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.SpinRevolverByRPM;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.SpinShooterByVision;
import frc.robot.vision.visionMainChallenge.Vision;
import frc.robot.yawControll.YawControl;
import frc.robot.yawControll.commands.SmartMoveTurretByVision;

public class AutonomousShootBalls extends ParallelCommandGroup {

    private final Arc arc;

    public AutonomousShootBalls(BallTrigger ballTrigger, Shooter shooter, Arc arc, YawControl yawControl, Vision vision,
                              Revolver revolver) {
        super(new SpinBallTriggerByRPM(ballTrigger, () -> BALL_TRIGGER_RPM),
                new SpinShooterByVision(shooter, vision),
                new SpinRevolverByRPM(revolver, () -> REVOLVER_RPM_WHILE_SHOOTING),
                new AutonomousMoveArcByVision(arc, vision),
                new SmartMoveTurretByVision(yawControl, vision),
                new ControlBallTriggerByConditions(ballTrigger, shooter::isOnTarget, revolver::isOnTarget,
                        ballTrigger::isOnTarget, arc::isOnTarget, yawControl::isOnTarget));
        this.arc = arc;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        CommandScheduler.getInstance().schedule(new CloseArc(arc));
    }
}

package frc.robot.crossPlatform;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.arc.Arc;
import frc.robot.arc.commands.CloseArc;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.SpinRevolverUntilLimitSwitch;
import frc.robot.shooter.Shooter;
import frc.robot.vision.visionMainChallenge.Vision;
import frc.robot.yawControll.YawControl;

import static frc.robot.crossPlatform.CrossPlatformConstants.ShooterConstantA.AUTONOMOUS_BALL_SHOOT_TIME;

// why does this class exist if never used?
public class AutonomousShootBalls extends SequentialCommandGroup {

    public AutonomousShootBalls(BallTrigger ballTrigger, Vision vision, Arc arc, YawControl yawControl,
                                Shooter shooter, Revolver revolver) {
        super(new AutonomousShootBallLogic(ballTrigger, shooter, arc, yawControl, vision, revolver)
                        .raceWith(new WaitCommand(AUTONOMOUS_BALL_SHOOT_TIME)),
                new CloseArc(arc).alongWith(new SpinRevolverUntilLimitSwitch(revolver)));
    }
}

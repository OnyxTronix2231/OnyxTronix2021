package frc.robot.crossPlatform;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.SpinBallTriggerByRPM;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.SpinShooterBySpeed;

import static frc.robot.ballTrigger.BallTriggerConstants.BACKWARD_BALL_TRIGGER_RPM;

public class DeputeCrossPlatformOi {

    public DeputeCrossPlatformOi(BallTrigger ballTrigger, Trigger moveTrigger){
        moveTrigger.whileActiveContinuous(new SpinBallTriggerByRPM(ballTrigger, ()-> BACKWARD_BALL_TRIGGER_RPM)
                .alongWith(new SpinShooterBySpeed(()-> -0.3)));
    }
}

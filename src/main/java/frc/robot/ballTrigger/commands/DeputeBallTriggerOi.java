package frc.robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.ballTrigger.BallTrigger;

import static frc.robot.ballTrigger.BallTriggerConstants.BACKWARD_BALL_TRIGGER_RPM;

public class DeputeBallTriggerOi {

    public DeputeBallTriggerOi(BallTrigger ballTrigger, Trigger moveTrigger) {
        moveTrigger.whileActiveContinuous(new SpinBallTriggerByRPM(ballTrigger, () -> BACKWARD_BALL_TRIGGER_RPM));
    }
}

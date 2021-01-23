package frc.robot.ballTrigger;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.ballTrigger.commands.MoveBallTriggerBySpeed;

public class DriverBallTriggerOIBinder {
    public DriverBallTriggerOIBinder(BallTrigger ballTrigger, Trigger moveBallTriggerBySpeed) {
        moveBallTriggerBySpeed.whileActiveOnce(new MoveBallTriggerBySpeed(ballTrigger, () -> 1));
    }
}

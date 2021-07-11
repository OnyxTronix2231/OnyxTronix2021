package frc.robot.ballTrigger;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.ballTrigger.commands.CloseBallTriggerPiston;
import frc.robot.ballTrigger.commands.OpenBallTriggerPiston;

public class DriverBallTriggerOiBinder {

    public  DriverBallTriggerOiBinder(BallTrigger ballTrigger, Trigger open){
        open.whenActive(new OpenBallTriggerPiston(ballTrigger));
        open.whenInactive(new CloseBallTriggerPiston(ballTrigger));
    }
}

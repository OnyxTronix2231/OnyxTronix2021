package frc.robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.ballTrigger.BallTrigger;

public class CloseBallTriggerPiston extends InstantCommand {

    public CloseBallTriggerPiston(BallTrigger ballTrigger) {
        super(ballTrigger::closePiston);
    }
}

package frc.robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.ballTrigger.BallTrigger;

public class CloseBallTriggerPistons extends InstantCommand {
    public CloseBallTriggerPistons(BallTrigger ballTrigger) {
        super(ballTrigger::closePistons);
    }
}

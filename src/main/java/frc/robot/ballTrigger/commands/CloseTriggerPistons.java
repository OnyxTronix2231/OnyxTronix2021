package frc.robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.ballTrigger.BallTrigger;

public class CloseTriggerPistons extends InstantCommand {
    public CloseTriggerPistons(BallTrigger ballTrigger) {
        super(ballTrigger::closePistons);
    }
}

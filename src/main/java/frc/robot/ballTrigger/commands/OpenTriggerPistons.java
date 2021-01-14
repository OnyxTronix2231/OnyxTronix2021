package frc.robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.ballTrigger.BallTrigger;

public class OpenTriggerPistons extends InstantCommand {
    public OpenTriggerPistons(BallTrigger ballTrigger) {
        super(ballTrigger::openPistons);
    }
}

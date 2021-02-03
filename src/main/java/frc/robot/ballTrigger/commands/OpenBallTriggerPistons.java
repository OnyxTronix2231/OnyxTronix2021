package frc.robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.ballTrigger.BallTrigger;

public class OpenBallTriggerPistons extends InstantCommand {

    public OpenBallTriggerPistons(BallTrigger ballTrigger) {
        super(ballTrigger::openPistons);
    }
}

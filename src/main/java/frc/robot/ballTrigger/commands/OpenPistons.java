package frc.robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.ballTrigger.BallTrigger;

public class OpenPistons extends InstantCommand {

    public OpenPistons(BallTrigger ballTrigger) {
        super(ballTrigger::openPistons);
    }
}

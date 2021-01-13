package frc.robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.ballTrigger.BallTrigger;

public class ClosePistons extends InstantCommand {

    public ClosePistons(BallTrigger ballTrigger) {
        super(ballTrigger::closePistons);
    }
}

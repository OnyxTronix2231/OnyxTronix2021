package frc.robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.ballTrigger.BallTrigger;

public class ClosePiston extends InstantCommand {

    public ClosePiston(BallTrigger ballTrigger) {
        super(ballTrigger::closePiston);
    }
}

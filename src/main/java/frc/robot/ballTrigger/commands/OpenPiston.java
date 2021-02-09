package frc.robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.ballTrigger.BallTrigger;

public class OpenPiston extends InstantCommand {

    public OpenPiston(BallTrigger ballTrigger) {
        super(ballTrigger::openPiston);
    }
}

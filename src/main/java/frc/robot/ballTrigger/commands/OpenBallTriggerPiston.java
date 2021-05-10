package frc.robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.ballTrigger.BallTrigger;

public class OpenBallTriggerPiston extends InstantCommand {

    public OpenBallTriggerPiston(BallTrigger ballTrigger) {
        super(ballTrigger::openPiston);
    }
}

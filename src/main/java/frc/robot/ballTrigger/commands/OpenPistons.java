package frc.robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.ballTrigger.Trigger;

public class OpenPistons extends InstantCommand {

    public OpenPistons(Trigger trigger) {
        super(trigger::openPistons);
    }
}

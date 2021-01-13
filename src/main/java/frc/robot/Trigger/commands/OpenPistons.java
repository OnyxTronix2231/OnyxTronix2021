package frc.robot.Trigger.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Trigger.Trigger;

public class OpenPistons extends InstantCommand {

    public OpenPistons(Trigger trigger) {
        super(trigger::openPistons);
    }
}

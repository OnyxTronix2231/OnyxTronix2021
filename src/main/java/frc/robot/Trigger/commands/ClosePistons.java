package frc.robot.Trigger.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Trigger.Trigger;

public class ClosePistons extends InstantCommand {

    public ClosePistons(Trigger trigger) {
        super(trigger::closePistons);
    }
}

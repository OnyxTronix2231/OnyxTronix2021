package frc.robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.ballTrigger.Trigger;

public class ClosePistons extends InstantCommand {

    public ClosePistons(Trigger trigger) {
        super(trigger::closePistons);
    }
}

package frc.robot.climber.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.climber.Climber;

public class ClosePistons extends InstantCommand {
    private final Climber climber;

    public ClosePistons(Climber climber) {
        this.climber = climber;
    }

    @Override
    public void initialize() {
        climber.closePiston();
    }
}

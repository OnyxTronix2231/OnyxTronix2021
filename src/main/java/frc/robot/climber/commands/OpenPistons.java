package frc.robot.climber.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.climber.Climber;

public class OpenPistons extends InstantCommand {
    private final Climber climber;

    public OpenPistons(Climber climber){
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.openPiston();
    }

}

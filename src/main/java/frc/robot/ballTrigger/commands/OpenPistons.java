package frc.robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ballTrigger.BallTrigger;

public class OpenPistons extends CommandBase {

    private final BallTrigger ballTrigger;

    public OpenPistons(final BallTrigger ballTrigger) {
        this.ballTrigger = ballTrigger;
        addRequirements(ballTrigger);
    }

    @Override
    public void initialize() {
        ballTrigger.openPistons();
    }
}

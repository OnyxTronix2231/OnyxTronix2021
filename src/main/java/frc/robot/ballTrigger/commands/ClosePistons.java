package frc.robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ballTrigger.BallTrigger;

public class ClosePistons extends CommandBase {

    private final BallTrigger ballTrigger;

    public ClosePistons(final BallTrigger ballTrigger) {
        this.ballTrigger = ballTrigger;
        addRequirements(ballTrigger);
    }

    @Override
    public void initialize() {
        ballTrigger.closePistons();
    }
}

package frc.robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ballCollector.BallCollector;

public class OpenPistons extends CommandBase {

    private final BallCollector ballCollector;

    public OpenPistons(final BallCollector ballCollector) {
        this.ballCollector = ballCollector;
        addRequirements(ballCollector);
    }

    @Override
    public void initialize() {
        ballCollector.openPistons();
    }
}

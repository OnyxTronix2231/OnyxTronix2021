package frc.robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ballTrigger.BallTrigger;

import java.util.function.DoubleSupplier;

public class SpinBallTriggerBySpeed extends CommandBase {

    private final BallTrigger ballTrigger;
    private final DoubleSupplier speedSupplier;

    public SpinBallTriggerBySpeed(BallTrigger ballTrigger, DoubleSupplier speedSupplier) {
        this.ballTrigger = ballTrigger;
        this.speedSupplier = speedSupplier;
        addRequirements(ballTrigger);
    }

    @Override
    public void execute() {
        ballTrigger.moveBySpeed(speedSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        ballTrigger.stop();
    }
}

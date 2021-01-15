package frc.robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ballTrigger.BallTrigger;

import java.util.function.DoubleSupplier;

public class MoveBallTriggerByRPM extends CommandBase {

    private final BallTrigger ballTrigger;
    private final DoubleSupplier RPMSupplier;

    public MoveBallTriggerByRPM(BallTrigger ballTrigger, DoubleSupplier RPMSupplier) {
        this.ballTrigger = ballTrigger;
        this.RPMSupplier = RPMSupplier;
        addRequirements(ballTrigger);
    }

    @Override
    public void initialize() {
        ballTrigger.moveBallTriggerByRPM(RPMSupplier.getAsDouble());
    }

    @Override
    public void execute() {
        ballTrigger.updateBallTriggerRPM(RPMSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        ballTrigger.stop();
    }
}

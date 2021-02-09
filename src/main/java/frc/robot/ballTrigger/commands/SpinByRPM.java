package frc.robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ballTrigger.BallTrigger;

import java.util.function.DoubleSupplier;

public class SpinByRPM extends CommandBase {

    private final BallTrigger ballTrigger;
    private final DoubleSupplier rpmSupplier;

    public SpinByRPM(BallTrigger ballTrigger, DoubleSupplier RPMSupplier) {
        this.ballTrigger = ballTrigger;
        this.rpmSupplier = RPMSupplier;
        addRequirements(ballTrigger);
    }

    @Override
    public void initialize() {
        ballTrigger.initMoveByRPM(rpmSupplier.getAsDouble());
    }

    @Override
    public void execute() {
        ballTrigger.updateMoveByRPM(rpmSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        ballTrigger.stop();
    }
}

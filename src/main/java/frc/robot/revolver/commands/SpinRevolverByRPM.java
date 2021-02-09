package frc.robot.revolver.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.revolver.Revolver;

import java.util.function.DoubleSupplier;

public class SpinRevolverByRPM extends CommandBase {

    private final Revolver revolver;
    private final DoubleSupplier rpmSupplier;

    public SpinRevolverByRPM(Revolver revolver, DoubleSupplier rpmSupplier) {
        this.revolver = revolver;
        this.rpmSupplier = rpmSupplier;
        addRequirements(revolver);
    }

    @Override
    public void initialize() {
        revolver.initMoveByRPM(rpmSupplier.getAsDouble());
    }

    @Override
    public void execute() {
        revolver.updateMoveByRPM(rpmSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        revolver.stop();
    }
}

package frc.robot.revolver.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.revolver.Revolver;

import java.util.function.DoubleSupplier;

public class SpinRevolverByRPM extends CommandBase {

    private final Revolver revolver;
    private final DoubleSupplier RPMSupplier;

    public SpinRevolverByRPM(Revolver revolver, DoubleSupplier RPMSupplier) {
        this.revolver = revolver;
        this.RPMSupplier = RPMSupplier;
        addRequirements(revolver);
    }

    @Override
    public void initialize() {
        revolver.initMoveByRPM(RPMSupplier.getAsDouble());
    }

    @Override
    public void execute() {
        revolver.updateMoveByRPM(RPMSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        revolver.stop();
    }
}

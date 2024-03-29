package frc.robot.revolver.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.revolver.Revolver;

import java.util.function.DoubleSupplier;

public class SpinRevolverBySpeed extends CommandBase {

    private final Revolver revolver;
    private final DoubleSupplier speedSupplier;

    public SpinRevolverBySpeed(Revolver revolver, DoubleSupplier speedSupplier) {
        this.revolver = revolver;
        this.speedSupplier = speedSupplier;
        addRequirements(revolver);
    }

    @Override
    public void execute() {
        revolver.moveBySpeed(speedSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        revolver.stop();
    }
}

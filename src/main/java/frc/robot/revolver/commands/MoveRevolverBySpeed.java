package frc.robot.revolver.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.revolver.Revolver;

import java.util.function.DoubleSupplier;

public class MoveRevolverBySpeed extends CommandBase {

    private final Revolver revolver;
    private final DoubleSupplier speedSupplier;

    public MoveRevolverBySpeed(final Revolver revolver, final DoubleSupplier speedSupplier) {
        this.revolver = revolver;
        this.speedSupplier = speedSupplier;
        addRequirements(revolver);
    }

    @Override
    public void execute() {
        revolver.moveRevolverBySpeed(speedSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        revolver.stopMotor();
    }
}

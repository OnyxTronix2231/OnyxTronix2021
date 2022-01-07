package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class SpinShooterBySpeed extends CommandBase {

    private final DoubleSupplier speedSupplier;

    public SpinShooterBySpeed(DoubleSupplier speedSupplier) {
        this.speedSupplier = speedSupplier;
        addRequirements(Shooter.getInstance());
    }

    @Override
    public void execute() {
        Shooter.getInstance().moveBySpeed(speedSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        Shooter.getInstance().stop();
    }
}

package frc.robot.flywheel.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.flywheel.Shooter;

import java.util.function.DoubleSupplier;

public class MoveShooterBySpeed extends CommandBase {

    private final Shooter shooter;
    private final DoubleSupplier speedSupplier;

    public MoveShooterBySpeed(Shooter shooter, DoubleSupplier speedSupplier) {
        this.shooter = shooter;
        this.speedSupplier = speedSupplier;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.moveShooterBySpeed(speedSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stop();
    }
}

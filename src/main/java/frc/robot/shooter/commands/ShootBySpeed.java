package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class ShootBySpeed extends CommandBase {

    private final Shooter shooter;
    private final DoubleSupplier speedSupplier;

    public ShootBySpeed(Shooter shooter, DoubleSupplier speedSupplier) {
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
        shooter.stopShooterMotor();
    }
}

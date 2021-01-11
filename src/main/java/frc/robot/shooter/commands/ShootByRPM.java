package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class ShootByRPM extends CommandBase {

    private final Shooter shooter;
    private final DoubleSupplier velocitySupplier;

    public ShootByRPM(final Shooter shooter, final DoubleSupplier velocitySupplier) {
        this.shooter = shooter;
        this.velocitySupplier = velocitySupplier;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.configVelocitySlot();
    }

    @Override
    public void execute() {
        shooter.setRPM(velocitySupplier.getAsDouble());
    }

    @Override
    public void end(final boolean interrupted) {
        shooter.stopMotor();
    }
}

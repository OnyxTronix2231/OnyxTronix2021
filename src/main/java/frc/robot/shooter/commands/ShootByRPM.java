package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class ShootByRPM extends CommandBase {

    private final Shooter shooter;
    private final DoubleSupplier RPMSupplier;

    public ShootByRPM(final Shooter shooter, final DoubleSupplier RPMSupplier) {
        this.shooter = shooter;
        this.RPMSupplier = RPMSupplier;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.setRPM(RPMSupplier.getAsDouble());
    }

    @Override
    public void end(final boolean interrupted) {
        shooter.stopMotor();
    }
}

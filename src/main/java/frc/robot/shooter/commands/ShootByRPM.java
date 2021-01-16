package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class ShootByRPM extends CommandBase {

    private final Shooter shooter;
    private final DoubleSupplier RPMSupplier;

    public ShootByRPM(Shooter shooter, DoubleSupplier RPMSupplier) {
        this.shooter = shooter;
        this.RPMSupplier = RPMSupplier;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.setRPM(RPMSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopShooterMotor();
    }
}

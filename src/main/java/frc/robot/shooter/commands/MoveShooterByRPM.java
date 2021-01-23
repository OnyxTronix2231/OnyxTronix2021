package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class MoveShooterByRPM extends CommandBase {

    private final Shooter shooter;
    private final DoubleSupplier RPMSupplier;

    public MoveShooterByRPM(Shooter shooter, DoubleSupplier RPMSupplier) {
        this.shooter = shooter;
        this.RPMSupplier = RPMSupplier;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.initMoveShooterByRPM(RPMSupplier.getAsDouble());
    }

    @Override
    public void execute() {
        shooter.updateMoveShooterByRPM(RPMSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stop();
    }
}

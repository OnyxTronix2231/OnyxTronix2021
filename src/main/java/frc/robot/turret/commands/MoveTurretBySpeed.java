package frc.robot.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveTurretBySpeed extends CommandBase {
    private Turret turret;
    private final DoubleSupplier speed;

    public MoveTurretBySpeed(Turret turret, DoubleSupplier speed) {
        this.turret = turret;
        this.speed = speed;
        addRequirements(turret);
    }

    @Override
    public void execute() {
        turret.MoveTurretBySpeed(speed.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        turret.stop();
    }
}

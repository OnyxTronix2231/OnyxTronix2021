package frc.robot.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveTurretBySpeed extends CommandBase {

    private final DoubleSupplier speed;
    private Turret turret;

    public MoveTurretBySpeed(Turret turret, DoubleSupplier speed) {
        this.turret = turret;
        this.speed = speed;
        addRequirements(turret);
    }

    @Override
    public void execute() {
        turret.moveBySpeed(speed.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        turret.stop();
    }
}

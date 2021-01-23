package frc.robot.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveTurretByAngleContinuously extends CommandBase {
    private final Turret turret;
    private final DoubleSupplier doubleSupplier;
    private final MoveTurretToAngle moveTurretToAngle;
    private double totalAngle;

    public MoveTurretByAngleContinuously(Turret turret, DoubleSupplier doubleSupplier) {
        this.turret = turret;
        this.doubleSupplier = doubleSupplier;
        moveTurretToAngle = new MoveTurretToAngle(turret, () -> totalAngle);
    }

    @Override
    public void initialize() {
        moveTurretToAngle.initialize();
        totalAngle = turret.getAngleRTR();
    }

    @Override
    public void execute() {
        totalAngle += doubleSupplier.getAsDouble();
        moveTurretToAngle.execute();
    }

    @Override
    public boolean isFinished() {
        return moveTurretToAngle.isFinished();
    }

    @Override
    public void end(boolean interrupted) {
        moveTurretToAngle.end(interrupted);
    }
}
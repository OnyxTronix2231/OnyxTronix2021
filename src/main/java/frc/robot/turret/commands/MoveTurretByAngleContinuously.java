package frc.robot.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveTurretByAngleContinuously extends CommandBase {
    private final Turret turret;
    private final DoubleSupplier doubleSupplier;
    private final MoveByAngle moveByAngle;
    private double totalAngle;

    public MoveTurretByAngleContinuously(Turret turret, DoubleSupplier doubleSupplier) {
        this.turret = turret;
        this.doubleSupplier = doubleSupplier;
        moveByAngle = new MoveByAngle(turret, () -> totalAngle);
    }

    @Override
    public void initialize() {
        moveByAngle.initialize();

    }

    @Override
    public void execute() {
        totalAngle += doubleSupplier.getAsDouble();
        moveByAngle.execute();
    }

    @Override
    public boolean isFinished() {
        return moveByAngle.isFinished();
    }

    @Override
    public void end(boolean interrupted) {
        moveByAngle.end(interrupted);
    }
}
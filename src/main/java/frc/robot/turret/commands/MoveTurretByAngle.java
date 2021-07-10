package frc.robot.turret.commands;

import frc.robot.turret.Turret;

import java.util.function.DoubleSupplier;
//why does this class exist if never used?
public class MoveTurretByAngle extends MoveTurretByAngleAndKeep {

    public MoveTurretByAngle(Turret turret, DoubleSupplier angleSupplier) {
        super(turret, angleSupplier);
    }

    @Override
    public boolean isFinished() {
        return turret.isOnTarget();
    }
}

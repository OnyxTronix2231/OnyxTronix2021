package frc.robot.turret.commands;

import frc.robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveTurretToAngle extends MoveTurretToAngleAndKeep {

    public MoveTurretToAngle(Turret turret, DoubleSupplier angleSupplier) {
        super(turret, angleSupplier);
    }

    @Override
    public boolean isFinished() {
        System.out.println(turret.isOnTarget());
        return turret.isOnTarget();
    }
}

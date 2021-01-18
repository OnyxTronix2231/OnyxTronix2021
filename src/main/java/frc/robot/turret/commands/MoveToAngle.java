package frc.robot.turret.commands;

import frc.robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveToAngle extends MoveToAngleAndKeep {

    public MoveToAngle(Turret turret, DoubleSupplier angle) {
        super(turret, angle);
    }

    @Override
    public boolean isFinished() {
        return getTurret().isOnTarget();
    }



}






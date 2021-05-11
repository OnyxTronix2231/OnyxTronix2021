package frc.robot.turret.commands;

import frc.robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveToAngleByVision {

    public MoveToAngleByVision(Turret turret, DoubleSupplier angleSupplier){
        super(turret, () -> angleSupplier.getAsDouble())
    }
}

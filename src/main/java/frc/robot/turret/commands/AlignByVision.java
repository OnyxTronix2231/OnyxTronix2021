package frc.robot.turret.commands;

import frc.robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class AlignByVision extends MoveToAngleAndKeep{
    public AlignByVision(Turret turret, DoubleSupplier angle) {
        super(turret, angle);
    }
}

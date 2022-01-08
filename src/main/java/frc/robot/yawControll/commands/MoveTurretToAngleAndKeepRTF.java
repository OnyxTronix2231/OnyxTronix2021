package frc.robot.yawControll.commands;

import frc.robot.turret.commands.MoveTurretToAngleAndKeep;
import frc.robot.yawControll.YawControl;

import java.util.function.DoubleSupplier;

public class MoveTurretToAngleAndKeepRTF extends MoveTurretToAngleAndKeep {

    public MoveTurretToAngleAndKeepRTF(YawControl yawControl, DoubleSupplier angleSupplier) {
        super(yawControl, () -> yawControl.angleToAngleRTF(angleSupplier.getAsDouble()));
    }
}

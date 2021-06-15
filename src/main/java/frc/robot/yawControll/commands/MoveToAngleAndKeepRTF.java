package frc.robot.yawControll.commands;

import frc.robot.turret.commands.MoveTurretToAngle;
import frc.robot.turret.commands.MoveTurretToAngleAndKeep;
import frc.robot.yawControll.YawControl;

import java.util.function.DoubleSupplier;

public class MoveToAngleAndKeepRTF extends MoveTurretToAngleAndKeep {

    public MoveToAngleAndKeepRTF(YawControl yawControl, DoubleSupplier angleSupplier) {
        super(yawControl, () -> yawControl.angleToAngleRTF(angleSupplier.getAsDouble()));
    }
}

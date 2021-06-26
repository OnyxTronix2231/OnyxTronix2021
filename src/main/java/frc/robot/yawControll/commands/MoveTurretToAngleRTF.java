package frc.robot.yawControll.commands;

import frc.robot.turret.Turret;
import frc.robot.yawControll.YawControl;

import java.util.function.DoubleSupplier;

public class MoveTurretToAngleRTF extends MoveTurretToAngleAndKeepRTF {
    public MoveTurretToAngleRTF(YawControl yawControl, DoubleSupplier angleSupplier) {
        super(yawControl, angleSupplier);
    }

    @Override
    public boolean isFinished() {
        return turret.isOnTarget();
    }
}

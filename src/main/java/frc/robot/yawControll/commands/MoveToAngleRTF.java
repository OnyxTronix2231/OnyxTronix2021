package frc.robot.yawControll.commands;

import frc.robot.turret.commands.MoveTurretToAngle;
import frc.robot.yawControll.YawControl;

import java.util.function.DoubleSupplier;

public class MoveToAngleRTF extends MoveTurretToAngle {

    public MoveToAngleRTF(YawControl yawControl, DoubleSupplier angleSupplier) {
        super(yawControl, ()-> angleSupplier.getAsDouble() - yawControl.getRobotAngle());
    }
}

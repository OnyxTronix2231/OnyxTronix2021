package frc.robot.yawControll.commands;

import frc.robot.turret.Turret;
import frc.robot.turret.commands.MoveTurretToAngle;
import frc.robot.yawControll.YawControl;

import java.util.function.DoubleSupplier;

public class MoveTurretToTargetArea extends MoveTurretToAngleRTF {

    public MoveTurretToTargetArea(YawControl yawControl) {
        super(yawControl, ()-> 180);
    }


}

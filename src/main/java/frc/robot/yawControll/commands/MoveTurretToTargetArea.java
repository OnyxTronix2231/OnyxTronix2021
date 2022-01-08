package frc.robot.yawControll.commands;

import frc.robot.yawControll.YawControl;

public class MoveTurretToTargetArea extends MoveTurretToAngleRTF {

    public MoveTurretToTargetArea(YawControl yawControl) {
        super(yawControl, ()-> 180);
    }
}

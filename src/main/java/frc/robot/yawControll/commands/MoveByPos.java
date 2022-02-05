package frc.robot.yawControll.commands;

import frc.robot.yawControll.YawControl;

public class MoveByPos extends MoveTurretToAngleAndKeepRTF {

    public MoveByPos(YawControl yawControl) {
        super(yawControl, yawControl::getAngleToTargetByPose);
        addRequirements(yawControl);
    }
}

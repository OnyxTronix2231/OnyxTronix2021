package frc.robot.yawControll;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.yawControll.commands.MoveTurretToAngleAndKeepRTF;

public class DriverYawControlOiBinder {

    public DriverYawControlOiBinder(YawControl yawControl, Trigger moveAndKeep) {
        moveAndKeep.whileActiveContinuous(new MoveTurretToAngleAndKeepRTF(yawControl, () -> 0));
    }
}

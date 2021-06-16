package frc.robot.yawControll;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.yawControll.commands.MoveToAngleAndKeepRTF;

public class DriverYawControlOiBinder {

    public DriverYawControlOiBinder(YawControl yawControl, Trigger moveAndKeep) {
        moveAndKeep.whileActiveContinuous(new MoveToAngleAndKeepRTF(yawControl, () -> 0));
    }
}

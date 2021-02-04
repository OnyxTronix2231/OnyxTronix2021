package frc.robot.flywheel;

import frc.robot.flywheel.commands.MoveFlywheelBySpeed;
import onyxTronix.JoystickAxis;

public class DriverFlywheelOiBinder {

    public DriverFlywheelOiBinder(Flywheel flywheel, JoystickAxis shootBySpeed) {
        shootBySpeed.whileActiveContinuous(new MoveFlywheelBySpeed(flywheel, shootBySpeed::getRawAxis));
    }
}

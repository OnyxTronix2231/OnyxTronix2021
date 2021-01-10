package frc.robot.turret;

import frc.robot.turret.commands.MoveBySpeed;
import onyxTronix.JoystickAxis;

public class DriverTurretOiBinder {

    public DriverTurretOiBinder(Turret turret, JoystickAxis moveBySpeed){
        moveBySpeed.whileActiveOnce(new MoveBySpeed(turret, moveBySpeed::getRawAxis));
    }
}

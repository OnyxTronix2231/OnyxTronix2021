package frc.robot.turret;

import frc.robot.turret.commands.MoveTurretByAngleContinuously;
import frc.robot.turret.commands.MoveTurretBySpeed;
import onyxTronix.JoystickAxis;

public class DriverTurretOiBinder {

    public DriverTurretOiBinder(Turret turret, JoystickAxis moveBySpeed, JoystickAxis moveByJoystickAngle){
        moveBySpeed.whileActiveOnce(new MoveTurretBySpeed(turret, moveBySpeed::getRawAxis));
        moveByJoystickAngle.whileActiveOnce(new MoveTurretByAngleContinuously(turret,moveByJoystickAngle::getRawAxis));
    }
}

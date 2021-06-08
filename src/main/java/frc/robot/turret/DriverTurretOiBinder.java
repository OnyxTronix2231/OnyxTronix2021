package frc.robot.turret;

import frc.robot.turret.commands.MoveTurretByAngle;
import frc.robot.turret.commands.MoveTurretByAngleContinuously;
import frc.robot.turret.commands.MoveTurretBySpeed;
import onyxTronix.JoystickAxis;

public class DriverTurretOiBinder {

    public DriverTurretOiBinder(Turret turret, JoystickAxis moveBySpeed, JoystickAxis moveByJoystickAngle) {
        //moveBySpeed.whileActiveOnce(new MoveTurretBySpeed(turret, moveBySpeed::getRawAxis));
        moveBySpeed.whileActiveOnce(new MoveTurretByAngle(turret, ()->

             moveBySpeed.getRawAxis() > 0 ? 32 : -32
         ));

//        moveByJoystickAngle.whileActiveOnce(new MoveTurretByAngleContinuously(turret, moveByJoystickAngle::getRawAxis));
    }
}

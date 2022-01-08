package frc.robot.turret;

import frc.robot.turret.commands.MoveTurretBySpeed;
import onyxTronix.JoystickAxis;

public class DeputeTurretOiBinder {

    public DeputeTurretOiBinder(Turret turret, JoystickAxis moveLeft, JoystickAxis moveRight){
        moveLeft.whileActiveContinuous(new MoveTurretBySpeed(turret, () -> moveLeft.getRawAxis() * -0.3));
        moveRight.whileActiveContinuous(new MoveTurretBySpeed(turret, () -> moveRight.getRawAxis() * 0.3));
    }
}

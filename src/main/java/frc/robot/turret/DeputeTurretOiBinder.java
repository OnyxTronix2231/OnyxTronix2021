package frc.robot.turret;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.turret.commands.MoveTurretBySpeed;
import frc.robot.turret.commands.MoveTurretToAngle;
import onyxTronix.JoystickAxis;

public class DeputeTurretOiBinder {

    public DeputeTurretOiBinder(Turret turret, JoystickAxis moveLeft, JoystickAxis moveRight, Trigger centerTurret){
        moveLeft.whileActiveContinuous(new MoveTurretBySpeed(turret, moveLeft::getRawAxis));
        moveRight.whileActiveContinuous(new MoveTurretBySpeed(turret, ()-> moveRight.getRawAxis() * -1));
        centerTurret.whileActiveContinuous(new MoveTurretToAngle(turret, ()-> 0));
    }
}

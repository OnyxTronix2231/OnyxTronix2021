package frc.robot.shooter;

import frc.robot.shooter.commands.MoveBySpeed;
import onyxTronix.JoystickAxis;

public class DriverShooterOiBinder {

    public DriverShooterOiBinder(Shooter shooter, JoystickAxis shootBySpeed) {
        shootBySpeed.whileActiveContinuous(new MoveBySpeed(shooter, shootBySpeed::getRawAxis));
    }
}

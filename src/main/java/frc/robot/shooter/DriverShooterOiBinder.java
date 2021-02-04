package frc.robot.shooter;

import frc.robot.shooter.commands.MoveBySpeed;
import onyxTronix.JoystickAxis;

public class DriverShooterOiBinder {

    public DriverShooterOiBinder(Shooter shooter, JoystickAxis shootByPresentOutput) {
        shootByPresentOutput.whileActiveContinuous(new MoveBySpeed(shooter, shootByPresentOutput::getRawAxis));
    }
}

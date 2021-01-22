package frc.robot.shooter;

import frc.robot.shooter.commands.MoveShooterBySpeed;
import onyxTronix.JoystickAxis;

public class DriverShooterOiBinder {

    public DriverShooterOiBinder(Shooter shooter, JoystickAxis shootByPresentOutput) {
        shootByPresentOutput.whileActiveContinuous(new MoveShooterBySpeed(shooter, shootByPresentOutput::getRawAxis));
    }
}

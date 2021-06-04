package frc.robot.shooter;

import frc.robot.shooter.commands.SpinShooterByRPM;
import frc.robot.shooter.commands.SpinShooterBySpeed;
import onyxTronix.JoystickAxis;

public class DriverShooterOiBinder {

    public DriverShooterOiBinder(Shooter shooter, JoystickAxis shootBySpeed) {
            shootBySpeed.whileActiveContinuous(new SpinShooterByRPM(shooter, ()-> 6000));
    }
}

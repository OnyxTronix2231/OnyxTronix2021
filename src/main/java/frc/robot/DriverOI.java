package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.shooter.DriverShooterOiBinder;
import frc.robot.shooter.Shooter;
import onyxTronix.JoystickAxis;

import static frc.robot.RobotConstants.DRIVER_JOYSTICK_PORT;

public class DriverOI {

    XboxController xboxController;

    public DriverOI(Shooter shooter) {
        xboxController = new XboxController(DRIVER_JOYSTICK_PORT);
        JoystickAxis shootTrigger = new JoystickAxis(xboxController, XboxController.Axis.kLeftY.value);
        new DriverShooterOiBinder(shooter, shootTrigger);
    }
}


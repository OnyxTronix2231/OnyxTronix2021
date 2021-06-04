package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.arc.DriverArcOiBinders;
import frc.robot.shooter.DriverShooterOiBinder;
import frc.robot.shooter.Shooter;
import onyxTronix.JoystickAxis;

import static frc.robot.RobotConstants.DRIVER_JOYSTICK_PORT;

public class DriverOI {

    XboxController xboxController;

    public DriverOI(Shooter shooter, Arc arc) {
        xboxController = new XboxController(DRIVER_JOYSTICK_PORT);
        JoystickAxis shootTrigger = new JoystickAxis(xboxController, XboxController.Axis.kLeftY.value);
        JoystickAxis moveArc  = new JoystickAxis(xboxController, XboxController.Axis.kRightY.value);
        Trigger changeAngle = new JoystickButton(xboxController, XboxController.Button.kB.value);
        Trigger calibrateArc = new JoystickButton(xboxController, XboxController.Button.kA.value);
        new DriverShooterOiBinder(shooter, shootTrigger);
        new DriverArcOiBinders(arc, changeAngle ,calibrateArc, moveArc );
    }
}


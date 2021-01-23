package frc.robot;

import static frc.robot.RobotConstants.DRIVER_JOYSTICK_PORT;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.arc.DriverArcOiBinders;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.shooter.DriverShooterOiBinder;
import frc.robot.shooter.Shooter;

public class DriverOI {

    public DriverOI(DriveTrain driveTrain, Shooter shooter, Arc arc) {
        XboxController xboxController = new XboxController(DRIVER_JOYSTICK_PORT);
        Trigger kAShootByRPM = new JoystickButton(xboxController, XboxController.Button.kA.value);
        Trigger kXChangeAngle = new JoystickButton(xboxController, XboxController.Button.kX.value);

        new DriverShooterOiBinder(shooter, kAShootByRPM);
        new DriverArcOiBinders(arc, kXChangeAngle);
    }
}

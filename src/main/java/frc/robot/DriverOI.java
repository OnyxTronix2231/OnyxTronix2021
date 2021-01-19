package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.DriveTrainOiBinder;
import frc.robot.shooter.DriverShooterOiBinder;
import frc.robot.shooter.Shooter;
import onyxTronix.JoystickAxis;

import static frc.robot.RobotConstants.DRIVER_JOYSTICK_PORT;

public class DriverOI {

    public DriverOI(DriveTrain driveTrain, Shooter shooter) {
        XboxController xboxController = new XboxController(DRIVER_JOYSTICK_PORT);

        new DriveTrainOiBinder(driveTrain, xboxController);

        Trigger kXChangeAngle = new JoystickButton(xboxController, XboxController.Button.kX.value);

        JoystickAxis shootByPresentOutPout = new JoystickAxis(xboxController, XboxController.Axis.kRightX.value);

        new DriverShooterOiBinder(shooter, kXChangeAngle, shootByPresentOutPout);
    }
}

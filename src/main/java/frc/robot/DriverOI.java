package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.DriveTrainOiBinder;

import static frc.robot.RobotConstants.DRIVER_JOYSTICK_PORT;

public class DriverOI {

    public DriverOI(DriveTrain driveTrain) {
        XboxController xboxController = new XboxController(DRIVER_JOYSTICK_PORT);
        Trigger pathButton = new JoystickButton(xboxController, XboxController.Button.kA.value);
        Trigger resetButton = new JoystickButton(xboxController, XboxController.Button.kB.value);

        new DriveTrainOiBinder(driveTrain, xboxController, pathButton, resetButton);
    }
}

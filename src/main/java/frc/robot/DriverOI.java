package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.collector.Collector;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.DriveTrainOiBinder;

import static frc.robot.RobotConstants.DRIVER_JOYSTICK_PORT;

public class DriverOI {

    public DriverOI(DriveTrain driveTrain, Collector collector) {
        XboxController xboxController = new XboxController(DRIVER_JOYSTICK_PORT);
        Trigger resetButton = new JoystickButton(xboxController, XboxController.Button.kB.value);
        Trigger pathButton = new JoystickButton(xboxController, XboxController.Button.kA.value);

        new DriveTrainOiBinder(driveTrain, collector, xboxController, resetButton, pathButton);
    }
}

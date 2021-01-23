package frc.robot;

import static frc.robot.RobotConstants.DRIVER_JOYSTICK_PORT;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Roulette.Roulette;
import frc.robot.Roulette.RouletteOIBinder;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.DriveTrainOiBinder;

public class DriverOI {

    public DriverOI(DriveTrain driveTrain, Roulette roulette) {
        XboxController xboxController = new XboxController(DRIVER_JOYSTICK_PORT);

        new DriveTrainOiBinder(driveTrain, xboxController);
        Trigger moveBySpeed = new JoystickButton(xboxController,XboxController.Button.kA.value);
        new RouletteOIBinder(roulette,moveBySpeed);
    }
}

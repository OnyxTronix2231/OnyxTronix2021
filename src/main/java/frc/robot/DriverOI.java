package frc.robot;

import static frc.robot.RobotConstants.DRIVER_JOYSTICK_PORT;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.DriveTrainOiBinder;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueTriggerCache;

import java.util.function.DoubleSupplier;

public class DriverOI {

  public DriverOI(DriveTrain driveTrain) {
    XboxController xboxController = new XboxController(DRIVER_JOYSTICK_PORT);

    JoystickAxis forwardAxis = new JoystickAxis(xboxController, XboxController.Axis.kLeftX.value);
    JoystickAxis rotateAxis = new JoystickAxis(xboxController, XboxController.Axis.kLeftY.value);
    Trigger moveByPathButton = new JoystickButton(xboxController, XboxController.Button.kA.value);
    Trigger reset = new JoystickButton(xboxController, XboxController.Button.kB.value);

    new DriveTrainOiBinder(driveTrain, forwardAxis, rotateAxis, moveByPathButton, reset);
  }
}

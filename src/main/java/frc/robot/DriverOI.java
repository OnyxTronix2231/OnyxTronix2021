package frc.robot;

import static frc.robot.RobotConstants.DRIVER_JOYSTICK_PORT;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.DriveTrainOiBinder;
import frc.robot.turret.DriverTurretOiBinder;
import frc.robot.turret.Turret;
import onyxTronix.JoystickAxis;

public class DriverOI {

  public DriverOI(DriveTrain driveTrain, Turret turret) {
    XboxController xboxController = new XboxController(DRIVER_JOYSTICK_PORT);


    new DriveTrainOiBinder(driveTrain, xboxController);


    JoystickAxis joystickAxis = new JoystickAxis(xboxController,XboxController.Axis.kLeftX.value);
    new DriverTurretOiBinder(turret, joystickAxis);
  }
}

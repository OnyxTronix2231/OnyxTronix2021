package frc.robot;

import static frc.robot.RobotConstants.DRIVER_JOYSTICK_PORT;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.DriveTrainOiBinder;

public class DriverOI {

  public DriverOI(DriveTrain driveTrain) {
    XboxController xboxController = new XboxController(DRIVER_JOYSTICK_PORT);

    new DriveTrainOiBinder(driveTrain, xboxController);
  }
}

package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.drivetrain.DriveTrain;

public class DriveByJoystick extends DriveBySpeed {
    public DriveByJoystick(DriveTrain driveTrain, XboxController driveJoystick) {
        super(driveTrain, () -> 0.8 * -driveJoystick.getRawAxis(XboxController.Axis.kLeftY.value),
                () -> 0.8 * driveJoystick.getRawAxis(XboxController.Axis.kRightX.value));
    }
}

package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.drivetrain.DriveTrain;

import static frc.robot.drivetrain.DriveTrainConstants.SLOW_DRIVE_SPEED_BY;

public class DriveByJoystick extends DriveBySpeed {
    public DriveByJoystick(DriveTrain driveTrain, XboxController driveJoystick) {
        super(driveTrain, () -> -driveJoystick.getRawAxis(XboxController.Axis.kLeftY.value),
                () -> driveJoystick.getRawAxis(XboxController.Axis.kRightX.value));
    }
}

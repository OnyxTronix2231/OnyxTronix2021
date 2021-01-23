package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.drivetrain.commands.DriveByJoystick;

public class DriveTrainOiBinder {
    public DriveTrainOiBinder(DriveTrain driveTrain, XboxController driveJoystick) {
        driveTrain.setDefaultCommand(new DriveByJoystick(driveTrain, driveJoystick));
    }
}

package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.drivetrain.commands.DriveByJoystick;

public class DriveTrainOiBinder {
    public DriveTrainOiBinder(DriveTrain driveTrain, XboxController driveJoystick, Trigger doPath, Trigger reset) {
        driveTrain.setDefaultCommand(new DriveByJoystick(driveTrain, driveJoystick));
    }
}

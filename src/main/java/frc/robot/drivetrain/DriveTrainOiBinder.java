package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.drivetrain.commands.DriveByJoystick;
import frc.robot.drivetrain.commands.SlowDriveTrainButton;

public class DriveTrainOiBinder {
    public DriveTrainOiBinder(DriveTrain driveTrain, XboxController driveJoystick, Trigger slow) {
        driveTrain.setDefaultCommand(new DriveByJoystick(driveTrain, driveJoystick));
        new SlowDriveTrainButton(driveTrain, slow);
    }
}

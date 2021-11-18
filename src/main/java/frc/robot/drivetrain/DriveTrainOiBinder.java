package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.drivetrain.commands.DriveByJoystick;
import frc.robot.drivetrain.commands.SlowDriveTrainButton;
import joysticks.ConsoleController;

public class DriveTrainOiBinder {
    public DriveTrainOiBinder(DriveTrain driveTrain, ConsoleController driveJoystick, Trigger slow) {
        driveTrain.setDefaultCommand(new DriveByJoystick(driveTrain, driveJoystick));
        new SlowDriveTrainButton(driveTrain, slow);
    }
}

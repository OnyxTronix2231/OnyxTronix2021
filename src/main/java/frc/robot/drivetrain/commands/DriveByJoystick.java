package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.drivetrain.DriveTrain;
import joysticks.ConsoleController;

public class DriveByJoystick extends DriveBySpeed {
    public DriveByJoystick(DriveTrain driveTrain, ConsoleController driveJoystick) {
        super(driveTrain, () -> -driveJoystick.getRawAxis(driveJoystick.getAxisLeftY()),
                () -> driveJoystick.getRawAxis(driveJoystick.getAxisRightX()));
    }
}

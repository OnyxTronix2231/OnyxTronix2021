package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.drivetrain.DriveTrain;

public class DriveByJoystick extends DriveBySpeed {
    public DriveByJoystick(DriveTrain driveTrain, XboxController driveJoystick) {
        super(driveTrain, () -> -driveJoystick.getY(GenericHID.Hand.kLeft),
                () -> driveJoystick.getX(GenericHID.Hand.kRight));
    }
}

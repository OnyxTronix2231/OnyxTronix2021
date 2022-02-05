

package frc.robot.drivetrain;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.drivetrain.commands.DriveBySpeed;
import frc.robot.drivetrain.commands.MoveByPath;
import onyxTronix.JoystickAxis;

import static frc.robot.drivetrain.DriveTrainConstants.Paths.*;

public class DriverDriveTrainOiBinders {

    public DriverDriveTrainOiBinders(DriveTrain driveTrain, JoystickAxis rightJoystick, JoystickAxis leftJoystick){
        driveTrain.setDefaultCommand(new DriveBySpeed(driveTrain, rightJoystick::getRawAxis, leftJoystick::getRawAxis));
    }


}

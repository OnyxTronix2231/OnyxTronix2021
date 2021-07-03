package frc.robot.drivetrain;

import static frc.robot.drivetrain.DriveTrainConstants.TrajectoryConstants.START_POSE;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.drivetrain.commands.DriveByJoystick;

public class DriveTrainOiBinder {
    public DriveTrainOiBinder(DriveTrain driveTrain, XboxController driveJoystick, Trigger reset) {
        driveTrain.setDefaultCommand(new DriveByJoystick(driveTrain, driveJoystick));
        reset.whenActive(new InstantCommand(() -> driveTrain.resetOdometryToPose(START_POSE)));
        }
}

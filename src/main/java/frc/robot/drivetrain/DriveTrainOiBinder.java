package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.drivetrain.commands.DriveByJoystick;

public class DriveTrainOiBinder {
  public DriveTrainOiBinder(DriveTrain driveTrain, XboxController driveJoystick, Trigger pathButton, Trigger resetButton) {
    driveTrain.setDefaultCommand(new DriveByJoystick(driveTrain, driveJoystick));
    pathButton.whileActiveOnce(driveTrain.getAutonomousCommand());
    resetButton.whenActive(new InstantCommand(() -> driveTrain.resetSimOdometryToPose(new Pose2d())));
  }
}

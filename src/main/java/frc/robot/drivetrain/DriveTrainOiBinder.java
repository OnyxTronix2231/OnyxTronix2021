package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.drivetrain.commands.DriveByJoystick;
import frc.robot.drivetrain.commands.MoveByPath;
import frc.robot.drivetrain.skills.SkillsConstants;

public class DriveTrainOiBinder {
  public DriveTrainOiBinder(DriveTrain driveTrain, XboxController driveJoystick, Trigger pathButton, Trigger resetButton) {
    driveTrain.setDefaultCommand(new DriveByJoystick(driveTrain, driveJoystick));
    pathButton.whileActiveOnce(new MoveByPath(driveTrain, SkillsConstants.Paths.TEST_PATH));
    resetButton.whenActive(new InstantCommand(() -> driveTrain.resetSimOdometryToPose(new Pose2d())));
  }
}

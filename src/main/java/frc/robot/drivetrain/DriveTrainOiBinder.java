package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.drivetrain.commands.DriveBySpeed;
import frc.robot.drivetrain.commands.MoveToPose;
import frc.robot.skillscompetiotion.SkillsConstants;
import onyxTronix.JoystickAxis;

public class DriveTrainOiBinder {
  public DriveTrainOiBinder(DriveTrain driveTrain, JoystickAxis forwardAxis, JoystickAxis rotateAxis, Trigger moveByPathButton, Trigger reset) {
    driveTrain.setDefaultCommand(new DriveBySpeed(driveTrain, () -> -forwardAxis.getRawAxis(), rotateAxis::getRawAxis));
    moveByPathButton.whileActiveOnce(new MoveToPose(driveTrain, new Pose(SkillsConstants.Waypoints.A3.getPose2dFromRotation(90))));
    reset.whenActive(new InstantCommand(() -> new Pose(SkillsConstants.Waypoints.A3.getPose2dFromRotation(90))));
  }
}

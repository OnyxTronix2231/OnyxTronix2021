package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.drivetrain.commands.DriveBySpeed;
import frc.robot.drivetrain.commands.MoveByPath;
import frc.robot.skillscompetiotion.SkillsConstants;
import onyxTronix.JoystickAxis;

public class DriveTrainOiBinder {
  public DriveTrainOiBinder(DriveTrain driveTrain, JoystickAxis forwardAxis, JoystickAxis rotateAxis, Trigger moveByPathButton, Trigger reset) {
    driveTrain.setDefaultCommand(new DriveBySpeed(driveTrain, () -> -forwardAxis.getRawAxis() * 0.001, () -> rotateAxis.getRawAxis() * 0.001));
    moveByPathButton.whenActive(new MoveByPath(driveTrain, SkillsConstants.Paths.AUTONAV_THIRD_A));
    reset.whenActive(new InstantCommand(() -> driveTrain.resetSimOdometryToPose(new Pose2d())));
  }
}

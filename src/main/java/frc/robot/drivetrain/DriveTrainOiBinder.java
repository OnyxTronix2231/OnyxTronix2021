package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.drivetrain.commands.DriveBySpeed;
import frc.robot.drivetrain.commands.MoveByPath;
import frc.robot.drivetrain.commands.MoveToPose;
import frc.robot.skillscompetiotion.SkillsConstants;
import onyxTronix.JoystickAxis;

public class DriveTrainOiBinder {
  public DriveTrainOiBinder(DriveTrain driveTrain, JoystickAxis forwardAxis, JoystickAxis rotateAxis,
                            Trigger moveByPathButton, Trigger moveToPoseButton, Trigger reset) {
    driveTrain.setDefaultCommand(new DriveBySpeed(driveTrain, () -> -forwardAxis.getRawAxis(), rotateAxis::getRawAxis));
    moveByPathButton.whileActiveOnce(new MoveByPath(driveTrain, SkillsConstants.Paths.AUTONAV_THIRD_A));
    moveToPoseButton.whileActiveOnce(new MoveToPose(driveTrain,new Pose(driveTrain.getPose().getX() + 3,
        driveTrain.getPose().getY(), driveTrain.getPose().getRotation().getDegrees())));
    reset.whenActive(new InstantCommand(() -> driveTrain.resetSimOdometryToPose(new Pose2d(1.2, 2.28, Rotation2d.fromDegrees(0)))));
  }
}

package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.drivetrain.commands.DriveByJoystick;
import frc.robot.drivetrain.commands.MoveByPath;
import frc.robot.drivetrain.skills.SkillsConstants;

import static frc.robot.drivetrain.DriveTrainConstants.TrajectoryConstants.START_POSE;
import static frc.robot.drivetrain.skills.SkillsConstants.Paths.TEST_PATH;
import static frc.robot.drivetrain.skills.SkillsConstants.Paths.TEST_PATH2;

public class DriveTrainOiBinder {
    public DriveTrainOiBinder(DriveTrain driveTrain, XboxController driveJoystick, Trigger resetButton) {
        driveTrain.setDefaultCommand(new DriveByJoystick(driveTrain, driveJoystick));
        resetButton.whenActive(new InstantCommand(() -> driveTrain.resetSimOdometryToPose(START_POSE)));
    }
}

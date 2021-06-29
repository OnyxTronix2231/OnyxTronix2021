package frc.robot.drivetrain;

import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.TONY_HAWK;
import static frc.robot.drivetrain.DriveTrainConstants.TrajectoryConstants.START_POSE;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.drivetrain.commands.DriveAndCollect;
import frc.robot.drivetrain.commands.DriveByJoystick;
import frc.robot.drivetrain.commands.MoveByPath;
import frc.robot.drivetrain.skills.SkillsConstants;

public class DriveTrainOiBinder {
    public DriveTrainOiBinder(DriveTrain driveTrain, XboxController driveJoystick, Trigger doPath, Trigger reset) {
        driveTrain.setDefaultCommand(new DriveByJoystick(driveTrain, driveJoystick));
        reset.whenActive(new InstantCommand(() -> driveTrain.resetOdometryToPose(START_POSE)));
        doPath.whenActive(new MoveByPath(driveTrain, TONY_HAWK));
    }
}

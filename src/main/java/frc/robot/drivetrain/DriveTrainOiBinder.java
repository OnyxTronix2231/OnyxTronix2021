package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.collector.Collector;
import frc.robot.drivetrain.commands.DriveAndCollect;
import frc.robot.drivetrain.commands.DriveByJoystick;
import frc.robot.drivetrain.skills.SkillsConstants;

import static frc.robot.drivetrain.DriveTrainConstants.TrajectoryConstants.START_POSE;

public class DriveTrainOiBinder {
    public DriveTrainOiBinder(DriveTrain driveTrain, Collector collector, XboxController driveJoystick, Trigger resetButton, Trigger pathButton) {
        driveTrain.setDefaultCommand(new DriveByJoystick(driveTrain, driveJoystick));
        resetButton.whenActive(new InstantCommand(() -> driveTrain.resetOdometryToPose(START_POSE)));
        pathButton.whenActive(new DriveAndCollect(driveTrain, collector, SkillsConstants.Paths.GALACTIC_SEARCH_RED_FIRST));
    }
}

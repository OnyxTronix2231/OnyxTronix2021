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
    public DriveTrainOiBinder(DriveTrain driveTrain, XboxController driveJoystick) {
        driveTrain.setDefaultCommand(new DriveByJoystick(driveTrain, driveJoystick));
    }
}

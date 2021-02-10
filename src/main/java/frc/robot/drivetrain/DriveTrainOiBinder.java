package frc.robot.drivetrain;

import static frc.robot.drivetrain.DriveTrainConstants.TrajectoryConstants.START_POSE;
import static frc.robot.drivetrain.skills.SkillsConstants.Paths.AUTONAV_THIRD_A;
import static frc.robot.drivetrain.skills.SkillsConstants.Paths.AUTONAV_THIRD_B;
import static frc.robot.drivetrain.skills.SkillsConstants.Paths.AUTONAV_THIRD_C;
import static frc.robot.drivetrain.skills.SkillsConstants.Paths.AUTONAV_THIRD_D;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.drivetrain.commands.MoveByPath;
import frc.robot.drivetrain.commands.MoveByShuffleboard;

public class DriveTrainOiBinder {
    public DriveTrainOiBinder(DriveTrain driveTrain, XboxController driveJoystick, Trigger resetButton, Trigger pathButton) {
        //driveTrain.setDefaultCommand(new MoveByShuffleboard(driveTrain));
        resetButton.whenActive(new InstantCommand(() -> driveTrain.resetSimOdometryToPose(START_POSE)));
        pathButton.whenActive(new MoveByPath(driveTrain, AUTONAV_THIRD_A)
                .andThen(new MoveByPath(driveTrain, AUTONAV_THIRD_B))
                .andThen(new MoveByPath(driveTrain, AUTONAV_THIRD_C))
                .andThen(new MoveByPath(driveTrain, AUTONAV_THIRD_D)));
    }
}

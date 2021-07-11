package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.drivetrain.DriveTrain;

import static frc.robot.drivetrain.DriveTrainConstants.ARCADE_DRIVE_BUTTON_SENSITIVITY;
import static frc.robot.drivetrain.DriveTrainConstants.ARCADE_DRIVE_SENSITIVITY;

public class SlowDriveTrainButton {

    public SlowDriveTrainButton(DriveTrain driveTrain, Trigger slowButton){
        slowButton.whileActiveContinuous(new InstantCommand(()-> driveTrain
                .setArcadeDriveSensitivity(ARCADE_DRIVE_BUTTON_SENSITIVITY)));
        slowButton.whenInactive(new InstantCommand(()-> driveTrain.setArcadeDriveSensitivity(ARCADE_DRIVE_SENSITIVITY)));
    }
}

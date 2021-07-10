package frc.robot.drivetrain;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.drivetrain.commands.SlowDriveTrainButton;

public class DeputeDriveTrainOiBinder {

    public DeputeDriveTrainOiBinder(DriveTrain driveTrain, Trigger slowButton){
        new SlowDriveTrainButton(driveTrain, slowButton);
    }
}

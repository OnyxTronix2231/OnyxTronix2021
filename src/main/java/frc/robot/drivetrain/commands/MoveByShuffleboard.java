package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.drivetrain.DriveTrain;

public class MoveByShuffleboard extends DriveBySpeed {

    public MoveByShuffleboard(DriveTrain driveTrain){
        super(driveTrain, () -> driveTrain.getPercentOutput(), () -> 0);
    }

}

package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.drivetrain.DriveTrain;

public class MoveByShuffleboard extends CommandBase {

    private final DriveTrain driveTrain;

    public MoveByShuffleboard(DriveTrain driveTrain){
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        driveTrain.tankDriveVolts(driveTrain.getShuffleboardVoltage(), driveTrain.getShuffleboardVoltage());
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.stopDrive();
    }
}

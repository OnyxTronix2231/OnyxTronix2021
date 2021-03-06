package frc.robot.drivetrain.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.drivetrain.DriveTrain;

public class MoveByShuffleboard extends CommandBase {

    private final DriveTrain driveTrain;
    private final NetworkTableEntry offsetEntry;

    public MoveByShuffleboard(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
        offsetEntry = Shuffleboard.getTab("DriveTrain").add("Offset", 1).getEntry();
        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (Robot.isSimulation()) {
//            driveTrain.tankDriveVolts(driveTrain.getShuffleboardVoltage(), driveTrain.getShuffleboardVoltage());
        } else {
//            driveTrain.tankDriveVolts(driveTrain.getShuffleboardVoltage() * offsetEntry.getDouble(1), driveTrain.getShuffleboardVoltage());
        }
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.stopDrive();
    }
}

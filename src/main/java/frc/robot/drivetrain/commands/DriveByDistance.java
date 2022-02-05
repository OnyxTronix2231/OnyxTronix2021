package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.drivetrain.DriveTrain;

import java.util.function.DoubleSupplier;

public class DriveByDistance extends CommandBase {

    private final DoubleSupplier distance;
    private final DriveTrain driveTrain;

    public DriveByDistance(DriveTrain driveTrain, DoubleSupplier distance) {
        this.distance = distance;
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
        driveTrain.inItDriveByDistance(distance.getAsDouble());
    }

    @Override
    public void execute() {
        driveTrain.updateDriveByDistance(distance.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return driveTrain.isOnTarget();
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.stop();
    }
}

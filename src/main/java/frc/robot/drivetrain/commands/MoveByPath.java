package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.utils.Path;

import static frc.robot.drivetrain.DriveTrainConstants.TrajectoryConstants.*;

public class MoveByPath extends CommandBase {
    private final DriveTrain driveTrain;
    private final Trajectory trajectory;
    private OnyxRamseteCommand command;

    public MoveByPath(DriveTrain driveTrain, Path path) {
        this.driveTrain = driveTrain;
        trajectory = path.toTrajectory();
        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
        command = new OnyxRamseteCommand(
                trajectory,
                driveTrain::getPose,
                new RamseteController(),
                FEEDFORWARD,
                FEEDFORWARD,
                DRIVE_KINEMATICS,
                driveTrain::getWheelSpeeds,
                new PIDController(TRAJECTORY_P, 0, 0),
                new PIDController(TRAJECTORY_P, 0, 0),
                driveTrain::tankDriveVolts,
                driveTrain
        );
        command.initialize();
    }

    @Override
    public void execute() {
        command.execute();
    }

    @Override
    public boolean isFinished() {
        return command.isFinished();
    }

    @Override
    public void end(boolean interrupted) {
        command.end(interrupted);
        driveTrain.stopDrive();
    }
}

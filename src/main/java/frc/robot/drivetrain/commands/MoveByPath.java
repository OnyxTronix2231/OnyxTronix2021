package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.utils.Path;

import static frc.robot.drivetrain.DriveTrainConstants.TrajectoryConstants.*;

public class MoveByPath extends CommandBase {
  private final DriveTrain driveTrain;
  private final Path path;
  private RamseteCommand command;

  public MoveByPath(DriveTrain driveTrain, Path path) {
    this.driveTrain = driveTrain;
    this.path = path;
  }

  @Override
  public void initialize() {
    command = new RamseteCommand(
        path.toTrajectory(driveTrain.getPose()),
        driveTrain::getPose,
        new RamseteController(RAMSETE_B, RAMSETE_ZETA),
        FEEDFORWARD,
        DRIVE_KINEMATICS,
        driveTrain::getWheelSpeeds,
        new PIDController(0.000008, 0, 0.000001),
        new PIDController(0.000008, 0, 0.000001),
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

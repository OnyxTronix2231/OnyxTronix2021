package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

import java.util.List;

import static frc.robot.drivetrain.DriveTrainConstants.TrajectoryConstants.*;

public class Path {
  private final Command command;

  public Path(DriveTrain driveTrain, Pose2d startPose, List<Translation2d> middlePoints, Pose2d endPose,
              boolean isReversed) {
    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(startPose, middlePoints, endPose,
        TRAJECTORY_CONFIG.setReversed(isReversed));
    RamseteCommand ramseteCommand = new RamseteCommand(
        trajectory,
        driveTrain::getPose,
        new RamseteController(RAMSETE_B, RAMSETE_ZETA),
        FEEDFORWARD,
        DRIVE_KINEMATICS,
        driveTrain::getWheelSpeeds,
        new PIDController(0.00008, 0, 0),
        new PIDController(0.00008, 0, 0),
        driveTrain::tankDriveVolts,
        driveTrain
    );
    command = ramseteCommand.andThen(() -> driveTrain.tankDriveVolts(0, 0));
  }

  public Command getCommand() {
    return command;
  }
}

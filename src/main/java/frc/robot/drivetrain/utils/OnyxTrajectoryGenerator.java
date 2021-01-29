package frc.robot.drivetrain.utils;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;

import java.util.List;

import static frc.robot.drivetrain.DriveTrainConstants.TrajectoryConstants.TRAJECTORY_CONFIG;

public class OnyxTrajectoryGenerator {
  public static Trajectory generateTrajectory(Pose2d startPose, Path path) {
    return TrajectoryGenerator.generateTrajectory(startPose, path.getMiddlePoints(),
        path.getEndPose(), TRAJECTORY_CONFIG.setReversed(path.isReversed()));
  }
}

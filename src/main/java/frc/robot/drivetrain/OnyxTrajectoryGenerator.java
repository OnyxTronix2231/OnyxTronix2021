package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;

import java.util.List;

public class OnyxTrajectoryGenerator {

  final TrajectoryConfig trajectoryConfig;

  public OnyxTrajectoryGenerator(TrajectoryConfig trajectoryConfig) {
    this.trajectoryConfig = trajectoryConfig;
  }

  public Trajectory getTrajectoryFromPoseList(List<Pose2d> poses, boolean isReversed) {
    System.out.println(poses.get(poses.size() - 1));
    trajectoryConfig.setReversed(isReversed);
    return TrajectoryGenerator.generateTrajectory(poses, trajectoryConfig);
  }
}

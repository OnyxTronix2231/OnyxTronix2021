package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.geometry.Pose2d;

public class Path {
  private final Pose[] poses;
  private final Pose endingPose;

  public Path(Pose... poses) {
    this.poses = poses;
    endingPose = poses[poses.length - 1];
  }

  public Path(boolean isReversed, Pose2d... poses) {
    Pose[] newPoses = new Pose[poses.length];
    int i = 0;
    for (Pose2d pose: poses) {
      newPoses[i] = new Pose(pose, isReversed);
      i++;
    }
    this.poses = newPoses;
    endingPose = newPoses[poses.length - 1];
  }

  public Pose getPoseAt(int i) {
    return poses[i];
  }

  public Pose getEndingPose() {
    return endingPose;
  }
}

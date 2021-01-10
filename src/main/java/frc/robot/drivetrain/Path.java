package frc.robot.drivetrain;

public class Path {
  private final robot.drivetrain.Pose[] poses;
  private final robot.drivetrain.Pose endingPose;

  public Path(final robot.drivetrain.Pose... poses) {
    this.poses = poses;
    endingPose = poses[poses.length - 1];
  }

  public robot.drivetrain.Pose getPoseAt(int i) {
    return poses[i];
  }

  public robot.drivetrain.Pose getEndingPose() {
    return endingPose;
  }
}

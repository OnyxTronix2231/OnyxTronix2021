package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.geometry.Pose2d;

import java.util.List;

public class Path {
  private final List<Pose2d> poses;
  private final boolean isReversed;

  public Path(Pose2d... pose2ds) {
    this.poses = List.of(pose2ds);
    isReversed = false;
  }

  public Path(boolean isReversed, Pose2d... poses2ds) {
    this.poses = List.of(poses2ds);
    this.isReversed = isReversed;
  }

  public Pose2d getPoseAt(int i) {
    return poses.get(i);
  }

  public List<Pose2d> getPoses() {
    return poses;
  }

  public boolean isReversed() {
    return isReversed;
  }
}

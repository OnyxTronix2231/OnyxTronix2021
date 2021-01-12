package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import frc.robot.skillscompetiotion.Waypoint;

public class Pose {

  private final Pose2d pose2d;
  private final boolean isForward;

  public Pose(Pose2d pose2d, boolean isForward) {
    this.pose2d = pose2d;
    this.isForward = isForward;
  }

  public Pose(Waypoint waypoint, Rotation2d rotation, boolean isForward) {
    pose2d = waypoint.getPose2dFromRotation(rotation);
    this.isForward = isForward;
  }

  public Pose2d getPose2d() {
    return pose2d;
  }

  public boolean isForward() {
    return isForward;
  }
}

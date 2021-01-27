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

  public Pose(Pose2d pose2d) {
    this.pose2d = pose2d;
    isForward = true;
  }

  public Pose(double x, double y, double deg, boolean isForward) {
    pose2d = new Pose2d(x, y, Rotation2d.fromDegrees(deg));
    this.isForward = isForward;
  }

  public Pose(double x, double y, double deg) {
    pose2d = new Pose2d(x, y, Rotation2d.fromDegrees(deg));
    this.isForward = true;
  }

  public Pose(Waypoint waypoint, double deg, boolean isForward) {
    pose2d = waypoint.getPose2dFromRotation(deg);
    this.isForward = isForward;
  }

  public Pose(Waypoint waypoint, double deg) {
    pose2d = waypoint.getPose2dFromRotation(deg);
    this.isForward = true;
  }

  public Pose2d getPose2d() {
    return pose2d;
  }

  public boolean isForward() {
    return isForward;
  }
}

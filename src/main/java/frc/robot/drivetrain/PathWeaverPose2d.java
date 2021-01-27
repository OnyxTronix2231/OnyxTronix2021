package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

public class PathWeaverPose2d extends Pose2d {
  private static final double OFFSET = 8.21055;

  public PathWeaverPose2d() {
    super();
  }

  public PathWeaverPose2d(double x, double y, double deg) {
    super(x, OFFSET - y, Rotation2d.fromDegrees(deg));
  }

  public PathWeaverPose2d(Pose2d pose) {
    super(pose.getX(), OFFSET - pose.getY(), pose.getRotation());
  }
}

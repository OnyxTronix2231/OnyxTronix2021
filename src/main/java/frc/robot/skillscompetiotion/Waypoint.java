package frc.robot.skillscompetiotion;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.drivetrain.PathWeaverPose2d;

public class Waypoint {
  private final double x;
  private final double y;

  public Waypoint(double x, double y) {
    this.x = Units.inchesToMeters(x);
    this.y = Units.inchesToMeters(y);
  }

  public Pose2d getPWPose2dFromRotation(double deg) {
    return new PathWeaverPose2d(x, y, deg);
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }
}

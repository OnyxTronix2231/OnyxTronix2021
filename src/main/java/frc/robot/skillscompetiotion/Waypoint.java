package frc.robot.skillscompetiotion;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

public class Waypoint {
  private final double x;
  private final double y;

  public Waypoint(double x, double y) {
    this.x = x * 0.0254;
    this.y = y * 0.0254;
  }

  public Pose2d getPose2dFromRotation(double deg){
    return new Pose2d(x, y, Rotation2d.fromDegrees(deg));
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }
}

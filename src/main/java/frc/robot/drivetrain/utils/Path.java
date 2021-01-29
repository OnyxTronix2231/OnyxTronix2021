package frc.robot.drivetrain.utils;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;

import java.util.List;

public class Path {
  private final boolean isReversed;
  private final List<Translation2d> middlePoints;
  private final Pose2d endPose;

  public Path(boolean isReversed, List<Translation2d> middlePoints, Pose2d endPose) {
    this.isReversed = isReversed;
    this.middlePoints = middlePoints;
    this.endPose = endPose;
  }

  public boolean isReversed() {
    return isReversed;
  }

  public List<Translation2d> getMiddlePoints() {
    return middlePoints;
  }

  public Pose2d getEndPose() {
    return endPose;
  }
}

package frc.robot.skillscompetiotion;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import frc.robot.drivetrain.Path;

import java.util.List;

public final class SkillsConstants {
  public static final class Waypoints {

    public static final Waypoint A1 = new Waypoint(30, 150);
    public static final Waypoint A3 = new Waypoint(90, 150);
    public static final Waypoint A4 = new Waypoint(120, 150);
    public static final Waypoint A6 = new Waypoint(180, 150);
    public static final Waypoint A7 = new Waypoint(210, 150);
    public static final Waypoint A8 = new Waypoint(240, 150);
    public static final Waypoint A9 = new Waypoint(270, 150);
    public static final Waypoint A10 = new Waypoint(300, 150);

    public static final Waypoint B1 = new Waypoint(30, 120);
    public static final Waypoint B2 = new Waypoint(60, 120);
    public static final Waypoint B3 = new Waypoint(90, 120);
    public static final Waypoint B4 = new Waypoint(120, 120);
    public static final Waypoint B5 = new Waypoint(150, 120);
    public static final Waypoint B6 = new Waypoint(180, 120);
    public static final Waypoint B7 = new Waypoint(210, 120);
    public static final Waypoint B8 = new Waypoint(240, 120);
    public static final Waypoint B9 = new Waypoint(270, 120);
    public static final Waypoint B10 = new Waypoint(300, 120);
    public static final Waypoint B11 = new Waypoint(330, 120);

    public static final Waypoint C3 = new Waypoint(90, 90);
    public static final Waypoint C9 = new Waypoint(270, 90);

    public static final Waypoint D1 = new Waypoint(30, 60);
    public static final Waypoint D2 = new Waypoint(60, 60);
    public static final Waypoint D3 = new Waypoint(90, 60);
    public static final Waypoint D4 = new Waypoint(120, 60);
    public static final Waypoint D5 = new Waypoint(150, 60);
    public static final Waypoint D6 = new Waypoint(180, 60);
    public static final Waypoint D7 = new Waypoint(210, 60);
    public static final Waypoint D8 = new Waypoint(240, 60);
    public static final Waypoint D9 = new Waypoint(270, 60);
    public static final Waypoint D10 = new Waypoint(300, 60);
    public static final Waypoint D11 = new Waypoint(330, 60);

    public static final Waypoint E1 = new Waypoint(30, 30);
    public static final Waypoint E3 = new Waypoint(90, 30);
    public static final Waypoint E4 = new Waypoint(120, 30);
    public static final Waypoint E6 = new Waypoint(180, 30);
    public static final Waypoint E7 = new Waypoint(210, 30);
    public static final Waypoint E8 = new Waypoint(240, 30);
    public static final Waypoint E9 = new Waypoint(270, 30);
    public static final Waypoint E10 = new Waypoint(300, 30);
  }

  public static final class Paths {

    private static final double OFFSET = 4.58;

    private static Pose2d getPose2dFromXYDeg(double x, double y, double deg) {
      return new Pose2d(x, y, Rotation2d.fromDegrees(deg));
    }

    public static final Path GALACTIC_RED_FIRST_PATH = new Path(List.of( //C3->D5->A6
        getPose2dFromXYDeg(0.3, 2.5, 0),
        Waypoints.C3.getPose2dFromRotation(Calculations.angleFromTangentVectors(1.164, -0.315)),
        Waypoints.D5.getPose2dFromRotation(Calculations.angleFromTangentVectors(0.549, 0.5)),
        Waypoints.A6.getPose2dFromRotation(Calculations.angleFromTangentVectors(0.912, 0.518)),
        getPose2dFromXYDeg(8.1, 4.3, 0)
    ), false);

    public final Path GALACTIC_RED_SECOND_PATH = new Path(List.of( //B3->D5-B7
        getPose2dFromXYDeg(0.3, 3.5, 0),
        Waypoints.B3.getPose2dFromRotation(Calculations.angleFromTangentVectors(1.132, -0.635)),
        Waypoints.D5.getPose2dFromRotation(Calculations.angleFromTangentVectors(1.318, -0.571)),
        Waypoints.B7.getPose2dFromRotation(Calculations.angleFromTangentVectors(1.278, 0.708)),
        getPose2dFromXYDeg(8.1, 3.5,0)
    ), false);

    public static final Path GALACTIC_BLUE_FIRST_PATH = new Path(List.of( //E6->B7->C9
        getPose2dFromXYDeg(0.3, 4.5, 0),
        Waypoints.E6.getPose2dFromRotation(Calculations.angleFromTangentVectors(1.052, 0.872)),
        Waypoints.B7.getPose2dFromRotation(Calculations.angleFromTangentVectors(-0.067, 2.75)),
        Waypoints.C9.getPose2dFromRotation(Calculations.angleFromTangentVectors(1.269, -0.56)),
        getPose2dFromXYDeg(8.1, 1.74, Calculations.angleFromTangentVectors(1.269, -0.56))
    ), false);

    public static final Path GALACTIC_BLUE_SECOND_PATH = new Path(List.of( //D6->B8->D10
        getPose2dFromXYDeg(0.3, 1, 0),
        Waypoints.D6.getPose2dFromRotation(Calculations.angleFromTangentVectors(1.145, 0.734)),
        Waypoints.B8.getPose2dFromRotation(Calculations.angleFromTangentVectors(1.484, 0.291)),
        Waypoints.D10.getPose2dFromRotation(Calculations.angleFromTangentVectors(1.229, -0.916)),
        getPose2dFromXYDeg(8.1, 1.2, Calculations.angleFromTangentVectors(1.229, -0.916))
    ), false);

    public static final Path AUTONAV_FIRST = new Path(List.of( //D6->B8->D10
        getPose2dFromXYDeg(1.2, OFFSET - 2.5, Calculations.angleFromTangentVectors(1.2, -0.4)),
        getPose2dFromXYDeg(1.85, OFFSET - 2.3, Calculations.angleFromTangentVectors(1.043, -0.204)),
        getPose2dFromXYDeg(4.3, OFFSET - 2.85, Calculations.angleFromTangentVectors(0.326, 1.39)),
        getPose2dFromXYDeg(4, OFFSET - 3.5, Calculations.angleFromTangentVectors(-0.295, 0.208)),
        getPose2dFromXYDeg(3.4, OFFSET - 3.5, Calculations.angleFromTangentVectors(-0.255, -0.228)),
        getPose2dFromXYDeg(3.5, OFFSET - 2.7, Calculations.angleFromTangentVectors(0.284, -0.635)),
        getPose2dFromXYDeg(5, OFFSET - 2.35, Calculations.angleFromTangentVectors(0.998, -0.275)),
        getPose2dFromXYDeg(6.5, OFFSET - 1.9, Calculations.angleFromTangentVectors(0.505, -0.517)),
        getPose2dFromXYDeg(6.9, OFFSET - 1.53, Calculations.angleFromTangentVectors(-0.034, -0.314)),
        getPose2dFromXYDeg(6.2, OFFSET - 1, Calculations.angleFromTangentVectors(-0.427, 0.059)),
        getPose2dFromXYDeg(5.6, OFFSET - 1.5, Calculations.angleFromTangentVectors(-0.704, 0.753)),
        getPose2dFromXYDeg(6.9, OFFSET - 3.3, Calculations.angleFromTangentVectors(0.922, 0.426)),
        getPose2dFromXYDeg(7.8, OFFSET - 3.5, Calculations.angleFromTangentVectors(0.472, -0.169)),
        getPose2dFromXYDeg(8.3, OFFSET - 2.75, Calculations.angleFromTangentVectors(1.233, -2.11)),
        getPose2dFromXYDeg(4.15, OFFSET - 1.9, Calculations.angleFromTangentVectors(-2.363, -0.22)),
        getPose2dFromXYDeg(0, OFFSET - 1.7, Calculations.angleFromTangentVectors(-0.5, -0.05))
        //Start between B2 and d2
        //around d5
        //around b8
        //around d10
        //back to the line between B2 and D2
    ), false);

    public static final Path AUTONAV_SECOND = new Path(List.of( //E6->B7->C9
        getPose2dFromXYDeg(1.2, OFFSET - 3.4, Calculations.angleFromTangentVectors(0.3, -0.2)),
        getPose2dFromXYDeg(3.1, OFFSET - 2.2, Calculations.angleFromTangentVectors(0.898, -0.61)),
        getPose2dFromXYDeg(4.69, OFFSET - 1.75, Calculations.angleFromTangentVectors(1.119, 0.054)),
        getPose2dFromXYDeg(6.4, OFFSET - 2.6, Calculations.angleFromTangentVectors(0.979, 0.742)),
        getPose2dFromXYDeg(7.6, OFFSET - 3.95, Calculations.angleFromTangentVectors(0.819, 0.053)),
        getPose2dFromXYDeg(8.8, OFFSET - 3, Calculations.angleFromTangentVectors(0.069, -0.622)),
        getPose2dFromXYDeg(7.69, OFFSET - 2, Calculations.angleFromTangentVectors(0.868, -0.206)),
        getPose2dFromXYDeg(6.3, OFFSET - 3.69, Calculations.angleFromTangentVectors(-1.049, 0.533)),
        getPose2dFromXYDeg(4.69, OFFSET - 4, Calculations.angleFromTangentVectors(-1.114, 0.019)),
        getPose2dFromXYDeg(3, OFFSET - 3.69, Calculations.angleFromTangentVectors(-1.715, -0.54)),
        getPose2dFromXYDeg(0, OFFSET - 1.5, Calculations.angleFromTangentVectors(-0.1, -0.2))
        //Start between D2 and (60,0)
        //up and over d4-d8
        //under and around d10
        //under d8-d4
        //finish at d2-b2
    ), false);

    public static final Path AUTONAV_THIRD_A = new Path(List.of(
        getPose2dFromXYDeg(1.2, OFFSET - 2.3, 0),
        Waypoints.A3.getPose2dFromRotation(90)
        //Start between B2 and D2
        //Finish in A3
    ), false);

    public static final Path AUTONAV_THIRD_B = new Path(List.of( //reverse
        Waypoints.A3.getPose2dFromRotation(90),
        getPose2dFromXYDeg(2.69, OFFSET - 2.5, Calculations.angleFromTangentVectors(0.552, 0.968)),
        getPose2dFromXYDeg(4, OFFSET - 3.8, Calculations.angleFromTangentVectors(0.554, -0.311)),
        getPose2dFromXYDeg(4, OFFSET - 2.35, Calculations.angleFromTangentVectors(0.192, -0.982)),
        Waypoints.A6.getPose2dFromRotation(270)
        //Start in A3
        //Go between b2 and b4
        //go between b4 and d3
        //around d5
        //Finish in a6
    ), false);

    public static final Path AUTONAV_THIRD_C = new Path(List.of(
        Waypoints.A6.getPose2dFromRotation(270),
        getPose2dFromXYDeg(5.2, OFFSET - 3.6, Calculations.angleFromTangentVectors(1.289, 0.273)),
        getPose2dFromXYDeg(6.3, OFFSET - 3.6, Calculations.angleFromTangentVectors(1.283, -0.272)),
        Waypoints.A9.getPose2dFromRotation(90)
        //Start in A6
        //between b5 and b7
        //around d7 and d8
        //Finish in a9
    ), false);

    public static final Path AUTONAV_THIRD_D = new Path(List.of( //reverse
        Waypoints.A9.getPose2dFromRotation(90),
        getPose2dFromXYDeg(7.9, OFFSET - 2.3, 0)
        //Start in A9
        //between b8 and b10
        //finish between b10 and d10
    ), false);
  }

  public static final class Calculations {
    public static double angleFromTangentVectors(double sizeOfX, double sizeOfY) {
      return Math.asin(sizeOfY / Math.sqrt(Math.pow(sizeOfX, 2) + Math.pow(sizeOfY, 2)));
    }
  }
}

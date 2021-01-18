package frc.robot.skillscompetiotion;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import frc.robot.drivetrain.Pose;

import java.util.List;
import java.math.*;

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

    public static final List<Pose> GALACTIC_RED_FIRST_PATH = List.of( //C3->D5->A6
        new Pose(0.3, 2.5, 0),
        new Pose(Waypoints.C3.getPose2dFromRotation(Calculations.angleFromTangentVectors(1.164, -0.315))),
        new Pose(Waypoints.D5.getPose2dFromRotation(Calculations.angleFromTangentVectors(0.549, 0.5))),
        new Pose(Waypoints.A6.getPose2dFromRotation(Calculations.angleFromTangentVectors(0.912, 0.518))),
        new Pose(8.1, 4.3, 0)
    );

    public static final List<Pose> GALACTIC_RED_SECOND_PATH = List.of( //B3->D5-B7
        new Pose(0.3, 3.5, 0),
        new Pose(Waypoints.B3.getPose2dFromRotation(Calculations.angleFromTangentVectors(1.132, -0.635))),
        new Pose(Waypoints.D5.getPose2dFromRotation(Calculations.angleFromTangentVectors(1.318, -0.571))),
        new Pose(Waypoints.B7.getPose2dFromRotation(Calculations.angleFromTangentVectors(1.278, 0.708))),
        new Pose(8.1, 3.5,0)
    );

    public static final List<Pose> GALACTIC_BLUE_FIRST_PATH = List.of( //E6->B7->C9
        new Pose(0.3, 4.5, 0),
        new Pose(Waypoints.E6.getPose2dFromRotation(Calculations.angleFromTangentVectors(1.052, 0.872))),
        new Pose(Waypoints.B7.getPose2dFromRotation(Calculations.angleFromTangentVectors(-0.067, 2.75))),
        new Pose(Waypoints.C9.getPose2dFromRotation(Calculations.angleFromTangentVectors(1.269, -0.56))),
        new Pose(8.1, 1.74, Calculations.angleFromTangentVectors(1.269, -0.56))
    );

    public static final List<Pose> GALACTIC_BLUE_SECOND_PATH = List.of( //D6->B8->D10
        new Pose(0.3, 1, 0),
        new Pose(Waypoints.D6.getPose2dFromRotation(Calculations.angleFromTangentVectors(1.145, 0.734))),
        new Pose(Waypoints.B8.getPose2dFromRotation(Calculations.angleFromTangentVectors(1.484, 0.291))),
        new Pose(Waypoints.D10.getPose2dFromRotation(Calculations.angleFromTangentVectors(1.229, -0.916))),
        new Pose(8.1, 1.2, Calculations.angleFromTangentVectors(1.229, -0.916))
    );

    public static final List<Pose> AUTONAV_FIRST = List.of( //D6->B8->D10
        new Pose(1.2, OFFSET - 2.5, Calculations.angleFromTangentVectors(1.2, -0.4)),
        new Pose(1.85, OFFSET - 2.3, Calculations.angleFromTangentVectors(1.043, -0.204)),
        new Pose(4.3, OFFSET - 2.85, Calculations.angleFromTangentVectors(0.326, 1.39)),
        new Pose(4, OFFSET - 3.5, Calculations.angleFromTangentVectors(-0.295, 0.208)),
        new Pose(3.4, OFFSET - 3.5, Calculations.angleFromTangentVectors(-0.255, -0.228)),
        new Pose(3.5, OFFSET - 2.7, Calculations.angleFromTangentVectors(0.284, -0.635)),
        new Pose(5, OFFSET - 2.35, Calculations.angleFromTangentVectors(0.998, -0.275)),
        new Pose(6.5, OFFSET - 1.9, Calculations.angleFromTangentVectors(0.505, -0.517)),
        new Pose(6.9, OFFSET - 1.53, Calculations.angleFromTangentVectors(-0.034, -0.314)),
        new Pose(6.2, OFFSET - 1, Calculations.angleFromTangentVectors(-0.427, 0.059)),
        new Pose(5.6, OFFSET - 1.5, Calculations.angleFromTangentVectors(-0.704, 0.753)),
        new Pose(6.9, OFFSET - 3.3, Calculations.angleFromTangentVectors(0.922, 0.426)),
        new Pose(7.8, OFFSET - 3.5, Calculations.angleFromTangentVectors(0.472, -0.169)),
        new Pose(8.3, OFFSET - 2.75, Calculations.angleFromTangentVectors(1.233, -2.11)),
        new Pose(4.15, OFFSET - 1.9, Calculations.angleFromTangentVectors(-2.363, -0.22)),
        new Pose(0, OFFSET - 1.7, Calculations.angleFromTangentVectors(-0.5, -0.05))
        //Start between B2 and d2
        //around d5
        //around b8
        //around d10
        //back to the line between B2 and D2
    );

    public static final List<Pose> AUTONAV_SECOND = List.of( //E6->B7->C9
        new Pose(1.2, OFFSET - 3.4, Calculations.angleFromTangentVectors(0.3, -0.2)),
        new Pose(3.1, OFFSET - 2.2, Calculations.angleFromTangentVectors(0.898, -0.61)),
        new Pose(4.69, OFFSET - 1.75, Calculations.angleFromTangentVectors(1.119, 0.054)),
        new Pose(6.4, OFFSET - 2.6, Calculations.angleFromTangentVectors(0.979, 0.742)),
        new Pose(7.6, OFFSET - 3.95, Calculations.angleFromTangentVectors(0.819, 0.053)),
        new Pose(8.8, OFFSET - 3, Calculations.angleFromTangentVectors(0.069, -0.622)),
        new Pose(7.69, OFFSET - 2, Calculations.angleFromTangentVectors(0.868, -0.206)),
        new Pose(6.3, OFFSET - 3.69, Calculations.angleFromTangentVectors(-1.049, 0.533)),
        new Pose(4.69, OFFSET - 4, Calculations.angleFromTangentVectors(-1.114, 0.019)),
        new Pose(3, OFFSET - 3.69, Calculations.angleFromTangentVectors(-1.715, -0.54)),
        new Pose(0, OFFSET - 1.5, Calculations.angleFromTangentVectors(-0.1, -0.2))
        //Start between D2 and (60,0)
        //up and over d4-d8
        //under and around d10
        //under d8-d4
        //finish at d2-b2
    );

    public static final List<Pose> AUTONAV_THIRD_A = List.of(
        new Pose(1.2, OFFSET - 2.3, 0),
        new Pose(Waypoints.A3, 90)
        //Start between B2 and D2
        //Finish in A3
    );

    public static final List<Pose> AUTONAV_THIRD_B = List.of(
        new Pose(Waypoints.A3, 90, false),
        new Pose(2.69, OFFSET - 2.5, Calculations.angleFromTangentVectors(0.552, 0.968), false),
        new Pose(4, OFFSET - 3.8, Calculations.angleFromTangentVectors(0.554, -0.311), false),
        new Pose(4, OFFSET - 2.35, Calculations.angleFromTangentVectors(0.192, -0.982), false),
        new Pose(Waypoints.A6, 270, false)
        //Start in A3
        //Go between b2 and b4
        //go between b4 and d3
        //around d5
        //Finish in a6
    );

    public static final List<Pose> AUTONAV_THIRD_C = List.of(
        new Pose(Waypoints.A6, 270),
        new Pose(5.2, OFFSET - 3.6, Calculations.angleFromTangentVectors(1.289, 0.273)),
        new Pose(6.3, OFFSET - 3.6, Calculations.angleFromTangentVectors(1.283, -0.272)),
        new Pose(Waypoints.A9, 90)
        //Start in A6
        //between b5 and b7
        //around d7 and d8
        //Finish in a9
    );

    public static final List<Pose> AUTONAV_THIRD_D = List.of(
        new Pose(Waypoints.A9, 90, false),
        new Pose(7.9, OFFSET - 2.3, 0, false)
        //Start in A9
        //between b8 and b10
        //finish between b10 and d10
    );
  }

  public static final class Calculations {
    public static double angleFromTangentVectors(double sizeOfX, double sizeOfY) {
      return Math.asin(sizeOfY / Math.sqrt(Math.pow(sizeOfX, 2) + Math.pow(sizeOfY, 2)));
    }
  }
}

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
        //Start between B2 and d2
        //around d5
        //around b8
        //around d10
        //back to the line between B2 and D2
    );

    public static final List<Pose> AUTONAV_SECOND = List.of( //E6->B7->C9
        //Start between D2 and (60,0)
        //up and over d4-d8
        //under and around d10
        //under d8-d4
        //finish at d2-b2
    );

    public static final List<Pose> AUTONAV_THIRD = List.of( //D6->B8->D10
        //Start between B2 and D2
        //Go to A3
        //Go between b2 and b4
        //go between b4 and d3
        //around d5
        //go to a6
        //between b5 and b7
        //around d7 and d8
        //go to a9
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

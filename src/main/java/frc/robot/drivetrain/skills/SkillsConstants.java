package frc.robot.drivetrain.skills;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.drivetrain.utils.Path;

import java.util.List;

public class SkillsConstants {
    public static final class Waypoints {

        private static Translation2d getWaypointFromInches(double x, double y) {
            return new Translation2d(Units.inchesToMeters(x), Units.inchesToMeters(y));
        }

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
        public static final List<Translation2d> GALACTIC_SEARCH_RED_FIRST =
                List.of(new Translation2d(Waypoints.C3.getX(), Waypoints.C3.getY()),
                        new Translation2d(Waypoints.D5.getX(), Waypoints.D5.getY()),
                        new Translation2d(Waypoints.A6.getX(), Waypoints.A6.getY()));

        public static final List<Translation2d> GALACTIC_SEARCH_RED_SECOND =
                List.of(new Translation2d(Waypoints.B3.getX(), Waypoints.B3.getY()),
                        new Translation2d(Waypoints.D5.getX(), Waypoints.D5.getY()),
                        new Translation2d(Waypoints.B7.getX(), Waypoints.B7.getY()));

        public static final List<Translation2d> GALACTIC_SEARCH_BLUE_FIRST =
                List.of(new Translation2d(Waypoints.E6.getX(), Waypoints.E6.getY()),
                        new Translation2d(Waypoints.B7.getX(), Waypoints.B7.getY()),
                        new Translation2d(Waypoints.C9.getX(), Waypoints.C9.getY()));

        public static final List<Translation2d> GALACTIC_SEARCH_BLUE_SECOND =
                List.of(new Translation2d(Waypoints.D6.getX(), Waypoints.D6.getY()),
                        new Translation2d(Waypoints.B8.getX(), Waypoints.B8.getY()),
                        new Translation2d(Waypoints.D10.getX(), Waypoints.D10.getY()));

        public static final Path TEST_PATH = new Path(false, List.of(),
                new Pose2d(3, 2, Rotation2d.fromDegrees(90)));
    }
}

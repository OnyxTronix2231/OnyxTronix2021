package frc.robot.drivetrain.skills;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.drivetrain.utils.Path;

import java.util.List;

public class SkillsConstants {
    public static final class Waypoints {

        public static final Translation2d A1 = getWaypointFromInches(30, 150);
        public static final Translation2d A3 = getWaypointFromInches(90, 150);
        public static final Translation2d A4 = getWaypointFromInches(120, 150);
        public static final Translation2d A6 = getWaypointFromInches(180, 150);
        public static final Translation2d A7 = getWaypointFromInches(210, 150);
        public static final Translation2d A8 = getWaypointFromInches(240, 150);
        public static final Translation2d A9 = getWaypointFromInches(270, 150);
        public static final Translation2d A10 = getWaypointFromInches(300, 150);
        public static final Translation2d B1 = getWaypointFromInches(30, 120);
        public static final Translation2d B2 = getWaypointFromInches(60, 120);
        public static final Translation2d B3 = getWaypointFromInches(90, 120);
        public static final Translation2d B4 = getWaypointFromInches(120, 120);
        public static final Translation2d B5 = getWaypointFromInches(150, 120);
        public static final Translation2d B6 = getWaypointFromInches(180, 120);
        public static final Translation2d B7 = getWaypointFromInches(210, 120);
        public static final Translation2d B8 = getWaypointFromInches(240, 120);
        public static final Translation2d B9 = getWaypointFromInches(270, 120);
        public static final Translation2d B10 = getWaypointFromInches(300, 120);
        public static final Translation2d B11 = getWaypointFromInches(330, 120);
        public static final Translation2d C3 = getWaypointFromInches(90, 90);
        public static final Translation2d C9 = getWaypointFromInches(270, 90);
        public static final Translation2d D1 = getWaypointFromInches(30, 60);
        public static final Translation2d D2 = getWaypointFromInches(60, 60);
        public static final Translation2d D3 = getWaypointFromInches(90, 60);
        public static final Translation2d D4 = getWaypointFromInches(120, 60);
        public static final Translation2d D5 = getWaypointFromInches(150, 60);
        public static final Translation2d D6 = getWaypointFromInches(180, 60);
        public static final Translation2d D7 = getWaypointFromInches(210, 60);
        public static final Translation2d D8 = getWaypointFromInches(240, 60);
        public static final Translation2d D9 = getWaypointFromInches(270, 60);
        public static final Translation2d D10 = getWaypointFromInches(300, 60);
        public static final Translation2d D11 = getWaypointFromInches(330, 60);
        public static final Translation2d E1 = getWaypointFromInches(30, 30);
        public static final Translation2d E3 = getWaypointFromInches(90, 30);
        public static final Translation2d E4 = getWaypointFromInches(120, 30);
        public static final Translation2d E6 = getWaypointFromInches(180, 30);
        public static final Translation2d E7 = getWaypointFromInches(210, 30);
        public static final Translation2d E8 = getWaypointFromInches(240, 30);
        public static final Translation2d E9 = getWaypointFromInches(270, 30);
        public static final Translation2d E10 = getWaypointFromInches(300, 30);

        private static Translation2d getWaypointFromInches(double x, double y) {
            return new Translation2d(Units.inchesToMeters(x), Units.inchesToMeters(y));
        }
    }

    public static final class Paths {
        public static final List<Translation2d> GALACTIC_SEARCH_RED_FIRST =
                List.of(Waypoints.C3,
                        Waypoints.D5,
                        Waypoints.A6);

        public static final List<Translation2d> GALACTIC_SEARCH_RED_SECOND =
                List.of(Waypoints.B3,
                        Waypoints.D5,
                        Waypoints.B7);

        public static final List<Translation2d> GALACTIC_SEARCH_BLUE_FIRST =
                List.of(Waypoints.E6,
                        Waypoints.B7,
                        Waypoints.C9);

        public static final List<Translation2d> GALACTIC_SEARCH_BLUE_SECOND =
                List.of(Waypoints.D6,
                        Waypoints.B8,
                        Waypoints.D10);

        public static final Path TEST_PATH = new Path(List.of(), new Pose2d(3, 2, Rotation2d.fromDegrees(90)));
    }
}

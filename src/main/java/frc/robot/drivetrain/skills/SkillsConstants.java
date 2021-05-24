package frc.robot.drivetrain.skills;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
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
        private static final double OFFSET = 8.2;
        public static final Path GALACTIC_SEARCH_RED_FIRST = new Path(List.of(
                Waypoints.C3,
                Waypoints.D5,
                Waypoints.A6
        ), new Pose2d(10, 3.81, Rotation2d.fromDegrees(0)));

        public static final Path GALACTIC_SEARCH_RED_SECOND = new Path(List.of(
                Waypoints.B3,
                Waypoints.D5,
                Waypoints.B7
        ), new Pose2d(10, 3.048, Rotation2d.fromDegrees(0)));

        public static final Path GALACTIC_SEARCH_BLUE_FIRST = new Path(List.of(
                Waypoints.E6,
                Waypoints.B7,
                Waypoints.C9
        ), new Pose2d(10, 2.286, Rotation2d.fromDegrees(0)));

        public static final Path GALACTIC_SEARCH_BLUE_SECOND = new Path(List.of(
                Waypoints.D6,
                Waypoints.B8,
                Waypoints.D10
        ), new Pose2d(15, 1.1, Rotation2d.fromDegrees(0)));

        public static final Path TEST_PATH = new Path(List.of(
        ), new Pose2d(6, OFFSET - 7.5, Rotation2d.fromDegrees(0)));

        public static final Path TEST_PATH2 = new Path(List.of(
                new Translation2d(4.9, OFFSET - 2.1),
                new Translation2d(7.69, OFFSET - 2.7),
                new Translation2d(6.75, OFFSET - 4),
                new Translation2d(6.15, OFFSET - 3.7),
                new Translation2d(7.1, OFFSET - 3)
        ), new Pose2d(5.95, OFFSET - 2.3, Rotation2d.fromDegrees(0))).setReversed();
    }
}

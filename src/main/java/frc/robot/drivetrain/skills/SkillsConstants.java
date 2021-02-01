package frc.robot.drivetrain.skills;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.constraint.CentripetalAccelerationConstraint;
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
        private static final double OFFSET = 4.572;

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

        public static final Path AUTONAV_FIRST = new Path(List.of(
            new Translation2d(4.4, OFFSET - 2.8),
            new Translation2d(3.9, OFFSET - 3.69),
            new Translation2d(3.3, OFFSET - 3.3),
            new Translation2d(3.5, OFFSET - 2.5),
            new Translation2d(5, OFFSET - 2.35),
            new Translation2d(6.6, OFFSET - 1.9),
            new Translation2d(6.9, OFFSET - 1.1),
            new Translation2d(6.1, OFFSET - 0.8),
            new Translation2d(5.5, OFFSET - 1.4),
            new Translation2d(7.3, OFFSET - 3.6),
            new Translation2d(8.3, OFFSET - 3.3),
            new Translation2d(8.2, OFFSET - 2.6),
            new Translation2d(4.2, OFFSET - 1.9)
        ), new Pose2d(0, OFFSET - 2, Rotation2d.fromDegrees(180)),
                new CentripetalAccelerationConstraint(15));

        public static final Path AUTONAV_SECOND = new Path(List.of(
                new Translation2d(3.2, OFFSET - 2.2),
                new Translation2d(4.69, OFFSET - 1.75),
                new Translation2d(6.4, OFFSET - 2.6),
                new Translation2d(7.6, OFFSET - 3.95),
                new Translation2d(8.8, OFFSET - 3),
                new Translation2d(7.69, OFFSET - 2),
                new Translation2d(6.3, OFFSET - 3.69),
                new Translation2d(4.69, OFFSET - 4),
                new Translation2d(3, OFFSET - 3.69)
        ), new Pose2d(0, OFFSET, Rotation2d.fromDegrees(130)));

        public static final Path AUTONAV_THIRD_A = new Path(List.of(),
                new Pose2d(Waypoints.A3.getX(), OFFSET - 1, Rotation2d.fromDegrees(90)));

        public static final Path AUTONAV_THIRD_B = new Path(List.of(
                new Translation2d(2.69, OFFSET - 2.5),
                new Translation2d(4, OFFSET - 3.8),
                new Translation2d(4.5, OFFSET - 2.35)
        ), new Pose2d(Waypoints.A6.getX(), OFFSET - 1, Rotation2d.fromDegrees(270))).setReversed();

        public static final Path AUTONAV_THIRD_C = new Path(List.of(
                new Translation2d(5.2, OFFSET - 3.6),
                new Translation2d(6.3, OFFSET - 3.6)
        ), new Pose2d(Waypoints.A9.getX(), OFFSET - 1, Rotation2d.fromDegrees(90)));

        public static final Path AUTONAV_THIRD_D = new Path(List.of(),
                new Pose2d(7.9, 2.3, Rotation2d.fromDegrees(180))).setReversed();
    }
}

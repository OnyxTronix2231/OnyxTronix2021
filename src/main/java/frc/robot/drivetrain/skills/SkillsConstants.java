package frc.robot.drivetrain.skills;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.constraint.CentripetalAccelerationConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.MaxVelocityConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.RectangularRegionConstraint;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.drivetrain.utils.Path;

import java.util.List;

//why does this whole class exist if never used?
public final class SkillsConstants {
    public static final class Waypoints {

//        public static final Translation2d A1 = getWaypointFromInches(30, 150);
        public static final Translation2d A3 = getWaypointFromInches(90, 150);
//        public static final Translation2d A4 = getWaypointFromInches(120, 150);
        public static final Translation2d A6 = getWaypointFromInches(180, 150);
//        public static final Translation2d A7 = getWaypointFromInches(210, 150);
//        public static final Translation2d A8 = getWaypointFromInches(240, 150);
        public static final Translation2d A9 = getWaypointFromInches(270, 150);
//        public static final Translation2d A10 = getWaypointFromInches(300, 150);

//        public static final Translation2d B1 = getWaypointFromInches(30, 120);
        public static final Translation2d B2 = getWaypointFromInches(60, 120);
        public static final Translation2d B3 = getWaypointFromInches(90, 120);
//        public static final Translation2d B4 = getWaypointFromInches(120, 120);
//        public static final Translation2d B5 = getWaypointFromInches(150, 120);
//        public static final Translation2d B6 = getWaypointFromInches(180, 120);
        public static final Translation2d B7 = getWaypointFromInches(210, 120);
        public static final Translation2d B8 = getWaypointFromInches(240, 120);
//        public static final Translation2d B9 = getWaypointFromInches(270, 120);
//        public static final Translation2d B10 = getWaypointFromInches(300, 120);
//        public static final Translation2d B11 = getWaypointFromInches(330, 120);

        public static final Translation2d C3 = getWaypointFromInches(90, 90);
        public static final Translation2d C9 = getWaypointFromInches(270, 90);

//        public static final Translation2d D1 = getWaypointFromInches(30, 60);
        public static final Translation2d D2 = getWaypointFromInches(60, 60);
//        public static final Translation2d D3 = getWaypointFromInches(90, 60);
//        public static final Translation2d D4 = getWaypointFromInches(120, 60);
        public static final Translation2d D5 = getWaypointFromInches(150, 60);
        public static final Translation2d D6 = getWaypointFromInches(180, 60);
//        public static final Translation2d D7 = getWaypointFromInches(210, 60);
//        public static final Translation2d D8 = getWaypointFromInches(240, 60);
//        public static final Translation2d D9 = getWaypointFromInches(270, 60);
        public static final Translation2d D10 = getWaypointFromInches(300, 60);
//        public static final Translation2d D11 = getWaypointFromInches(330, 60);

//        public static final Translation2d E1 = getWaypointFromInches(30, 30);
//        public static final Translation2d E3 = getWaypointFromInches(90, 30);
//        public static final Translation2d E4 = getWaypointFromInches(120, 30);
        public static final Translation2d E6 = getWaypointFromInches(180, 30);
//        public static final Translation2d E7 = getWaypointFromInches(210, 30);
//        public static final Translation2d E8 = getWaypointFromInches(240, 30);
//        public static final Translation2d E9 = getWaypointFromInches(270, 30);
//        public static final Translation2d E10 = getWaypointFromInches(300, 30);

        private static Translation2d getWaypointFromInches(double x, double y) {
            return new Translation2d(Units.inchesToMeters(x), Units.inchesToMeters(y));
        }
    }

    public static final class StartingPositions {

        public static final Pose2d GS_RED_FIRST_START = new Pose2d(0.23, 3.4, Rotation2d.fromDegrees(0));
        public static final Pose2d GS_RED_SECOND_START = new Pose2d(0.3, 3.4, Rotation2d.fromDegrees(0));
        public static final Pose2d GS_BLUE_FIRST_START = new Pose2d(0.3, 1, Rotation2d.fromDegrees(0));
        public static final Pose2d GS_BLUE_SECOND_START = new Pose2d(0.3, 1, Rotation2d.fromDegrees(0));
        public static final Pose2d AUTONAV_FIRST_START = new Pose2d(Waypoints.B2.getX() - 0.42, Waypoints.B2.getY() - 0.75, Rotation2d.fromDegrees(0));
        public static final Pose2d AUTONAV_SECOND_START = new Pose2d(Waypoints.D2.getX() - 0.42, Waypoints.D2.getY() - 0.75, Rotation2d.fromDegrees(0));
        public static final Pose2d AUTONAV_THIRD_START = new Pose2d(Waypoints.B2.getX() - 0.42, Waypoints.B2.getY() - 0.75, Rotation2d.fromDegrees(0));
//        private static final double OFFSET = 4.572;
    }

    public static final class Paths {
        public static final Path GALACTIC_SEARCH_RED_FIRST = new Path(
                StartingPositions.GS_RED_FIRST_START, List.of(
                Waypoints.C3,
                Waypoints.D5,
                Waypoints.A6
        ), new Pose2d(10, 3.8, Rotation2d.fromDegrees(0)),
                new RectangularRegionConstraint(new Translation2d(Waypoints.D5.getX(), Waypoints.D5.getY()), new Translation2d(),
                        new CentripetalAccelerationConstraint(6)));

        public static final Path GALACTIC_SEARCH_RED_SECOND = new Path(
                StartingPositions.GS_RED_SECOND_START, List.of(
                Waypoints.B3,
                Waypoints.D5,
                Waypoints.B7
        ), new Pose2d(10, 3.3, Rotation2d.fromDegrees(0)), new CentripetalAccelerationConstraint(10));

        public static final Path GALACTIC_SEARCH_BLUE_FIRST = new Path(
                StartingPositions.GS_BLUE_FIRST_START, List.of(
                Waypoints.E6,
                Waypoints.B7,
                new Translation2d(Waypoints.C9.getX(), Waypoints.C9.getY() - 0.3)
        ), new Pose2d(10, 2.3, Rotation2d.fromDegrees(-20)), new CentripetalAccelerationConstraint(3),
                new MaxVelocityConstraint(2.5), new RectangularRegionConstraint(new Translation2d(
                Waypoints.B7.getX() - 0.2, Waypoints.B7.getY() - 0.3),
                new Translation2d(
                        Waypoints.C9.getX() - 0.2, Waypoints.C9.getY() - 0.3),
                new CentripetalAccelerationConstraint(3)
        ));

        public static final Path GALACTIC_SEARCH_BLUE_SECOND = new Path(
                StartingPositions.GS_BLUE_SECOND_START, List.of(
                Waypoints.D6,
                Waypoints.B8,
                new Translation2d(Waypoints.D10.getX(), Waypoints.D10.getY() - 0.15)
        ), new Pose2d(15, 1.1, Rotation2d.fromDegrees(0)), new CentripetalAccelerationConstraint(6),
                new MaxVelocityConstraint(2));
//        public static final Path AUTONAV_THIRD_D = new Path(AUTONAV_THIRD_C.getEndPose(), List.of(),
//                new Pose2d(7.9, 2.3, Rotation2d.fromDegrees(180))).setReversed();
        private static final double OFFSET = 4.572;
        public static final Path AUTONAV_FIRST = new Path(StartingPositions.AUTONAV_FIRST_START,
                List.of(new Translation2d(4.4, OFFSET - 2.8),
                        new Translation2d(3.9, OFFSET - 3.7),
                        new Translation2d(3.3, OFFSET - 3.3),
                        new Translation2d(3.5, OFFSET - 2.5),
                        new Translation2d(5, OFFSET - 2.35),
                        new Translation2d(6.7, OFFSET - 2),
                        new Translation2d(6.9, OFFSET - 1.1),
                        new Translation2d(6.1, OFFSET - 0.8),
                        new Translation2d(5.4, OFFSET - 1.4),
                        new Translation2d(7.125, OFFSET - 3.45),
                        new Translation2d(8.16, OFFSET - 3.3),
                        new Translation2d(8.16, OFFSET - 2.6),
                        new Translation2d(6.11, OFFSET - 2.256)
                ), new Pose2d(0, OFFSET - 2.2, Rotation2d.fromDegrees(180)),
                new CentripetalAccelerationConstraint(3.2));
        public static final Path AUTONAV_SECOND = new Path(StartingPositions.AUTONAV_SECOND_START,
                List.of(
                        new Translation2d(3.1, OFFSET - 2.1),
                        new Translation2d(6.3, OFFSET - 2.4),
                        new Translation2d(6.8, OFFSET - 3.3),
                        new Translation2d(7.6, OFFSET - 3.95),
                        new Translation2d(8.3, OFFSET - 3.6),
                        new Translation2d(8.5, OFFSET - 2.7),
                        new Translation2d(7.7, OFFSET - 2),
                        new Translation2d(6.9, OFFSET - 2.55),
                        new Translation2d(6.55, OFFSET - 3.83),
                        new Translation2d(4.7, OFFSET - 4),
                        new Translation2d(2.75, OFFSET - 3.8),
                        new Translation2d(2.15, OFFSET - 2.74)
                ), new Pose2d(1.55, OFFSET - 2.3, Rotation2d.fromDegrees(130)), new CentripetalAccelerationConstraint(10),
                new RectangularRegionConstraint(new Translation2d(6.3, 0), new Translation2d(9, OFFSET), new CentripetalAccelerationConstraint(3)),
                new RectangularRegionConstraint(new Translation2d(1.5, -3.8), new Translation2d(3.1, OFFSET - 2.1), new CentripetalAccelerationConstraint(6)));
        public static final Path AUTONAV_THIRD_A = new Path(StartingPositions.AUTONAV_THIRD_START,
                List.of(),
                new Pose2d(Waypoints.A3.getX(), OFFSET - 1.2, Rotation2d.fromDegrees(90)));
        public static final Path AUTONAV_THIRD_B = new Path(AUTONAV_THIRD_A.getEndPose(), List.of(
                new Translation2d(2.698, OFFSET - 2.54),
                new Translation2d(3.45, OFFSET - 3.778),
                new Translation2d(4.28, OFFSET - 3.7),
                new Translation2d(4.5, OFFSET - 2.5)
        ), new Pose2d(Waypoints.A6.getX(), OFFSET - 1.11, Rotation2d.fromDegrees(270)), new CentripetalAccelerationConstraint(6)).setReversed();
        public static final Path AUTONAV_THIRD_C = new Path(AUTONAV_THIRD_B.getEndPose(), List.of(
                new Translation2d(5.09, OFFSET - 3.6),
                new Translation2d(6.335, OFFSET - 3.6)
        ), new Pose2d(Waypoints.A9.getX(), OFFSET - 1.105, Rotation2d.fromDegrees(90)), new CentripetalAccelerationConstraint(5));
    }
}

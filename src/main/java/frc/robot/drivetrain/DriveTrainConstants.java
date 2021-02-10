package frc.robot.drivetrain;

import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.CONVERSION_RATE;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.ENCODER_UNITS;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.drivetrain.utils.Path;

import java.util.List;

public final class DriveTrainConstants {

    public static final double WHEEL_DIAMETER_METER = Units.inchesToMeters(6);
    public static final double PERIMETER_METER = WHEEL_DIAMETER_METER * Math.PI;
    static final int DECISECOND_IN_SECOND = 10;
    static final double ARCADE_DRIVE_ROTATION_SENSITIVITY = 1; // TODO: check value
    static final double ARCADE_DRIVE_FORWARD_SENSITIVITY = 1; // TODO: check value

    public static final class DriveTrainSimConstantsA {
        static final double DRIVE_TRAIN_MASS = 54;
        static final double MOMENT_OF_INERTIA = 5.5;
    }

    public static final class DriveTrainComponentsA {

        static final int LEFT_MASTER_PORT = 2; // TODO: check value
        static final int LEFT_SLAVE_PORT = 3; // TODO: check value
        static final int RIGHT_MASTER_PORT = 0; // TODO: check value
        static final int RIGHT_SLAVE_PORT = 1; // TODO: check value
        static final int PIGEON_PORT = 0;
        static final int ENCODER_UNITS = 2048;
        static final int CONTINUOUS_CURRENT_LIMIT = 10;
        static final int PEAK_CURRENT_DURATION = 1000;
        static final double TRIGGER_THRESHOLD_CURRENT = 40; // TODO: check value
        static final double TRIGGER_THRESHOLD_TIME = 0.03; // TODO: check value
        static final double MAX_OUTPUT_FORWARD = 1; // TODO: check value
        static final double MAX_OUTPUT_REVERSE = -1; // TODO: check value
        static final double CURRENT_LIMIT = 40; // TODO: check value
        static final double CONVERSION_RATE = 9;
        static final double CLOSED_LOOP_RAMP = 0;
        static final double OPEN_LOOP_RAMP = 0;
    }

    public static final class TrajectoryConstants {

        public static final double TRAJECTORY_P = 0;
        public static final double RAMSETE_B = 2;
        public static final double RAMSETE_ZETA = 0.7;
        public static final double ENCODER_CPR = ENCODER_UNITS * CONVERSION_RATE; // TODO: Calibration with A
        public static final double MAX_SPEED_METERS_PER_SECOND = 5.5; // TODO: Calibration with A
        public static final double MAX_ACCELERATION_METERS_PER_SECOND_SQUARED = 6.9; // TODO: Calibration with A
        public static final Pose2d START_POSE = new Pose2d(0.3, 2, Rotation2d.fromDegrees(0));
        static final double TRACKWIDTH_METERS = 0.675; // TODO: Calibration with A
        public static final DifferentialDriveKinematics DRIVE_KINEMATICS =
                new DifferentialDriveKinematics(TRACKWIDTH_METERS);
        private static final double kS = 0.480938; // TODO: This is Sim value, Calibration with A
        private static final double kV = 2.1073; // TODO: This is Sim value, Calibration with A
        private static final double kA = 0.3144; // TODO: Calibration with A
        public static final SimpleMotorFeedforward FEEDFORWARD = new SimpleMotorFeedforward(kS, kV, kA);
    }

    public static final class InfiniteRechargePaths {
        private static final double OFFSET = 8.2;

        public static final Path PRIORITY_PATH_A = new Path(List.of(),
                new Pose2d(6, OFFSET - 7.5, Rotation2d.fromDegrees(0)));

        public static final Path PRIORITY_PATH_B = new Path(List.of(
                new Translation2d(4.9, OFFSET - 2.1),
                new Translation2d(7.69, OFFSET - 2.7),
                new Translation2d(6.75, OFFSET - 4),
                new Translation2d(6.15, OFFSET - 3.7),
                new Translation2d(7.1, OFFSET - 3)
        ), new Pose2d(5.95, OFFSET - 2.3, Rotation2d.fromDegrees(0))).setReversed();

        public static final Path SECOND_PRIORITY_PATH_A = new Path(List.of(),
                new Pose2d(7, OFFSET - 0.75, Rotation2d.fromDegrees(0)));

        public static final Path SECOND_PRIORITY_PATH_B_AND_SECOND_FOURTH_AND_FIFTH_D = new Path(List.of(),
                new Pose2d(5.5, OFFSET - 0.75, Rotation2d.fromDegrees(0))).setReversed();

        public static final Path SECOND_FOURTH_AND_FIFTH_PRIORITY_PATH_C = new Path(List.of(),
                new Pose2d(9.5, OFFSET - 0.75, Rotation2d.fromDegrees(0)));

        public static final Path THIRD_PRIORITY_PATH_A = new Path(List.of(),
                new Pose2d(6, OFFSET - 7.5, Rotation2d.fromDegrees(0)));

        public static final Path THIRD_PRIORITY_PATH_B = new Path(List.of(),
                new Pose2d(4, OFFSET - 4.1, Rotation2d.fromDegrees(225))).setReversed();

        public static final Path THIRD_PRIORITY_PATH_C = new Path(List.of(
                new Translation2d(6.5, OFFSET - 3.3),
                new Translation2d(7.2, OFFSET - 3),
                new Translation2d(7.5, OFFSET - 3.7),
                new Translation2d(6.7, OFFSET - 4)
        ), new Pose2d(5, OFFSET - 4.6, Rotation2d.fromDegrees(225)));

        public static final Path FOURTH_PRIORITY_PATH_A = new Path(List.of(),
                new Pose2d(8.5, OFFSET - 2.5, Rotation2d.fromDegrees(45)));

        public static final Path FOURTH_PRIORITY_PATH_B = new Path(List.of(
                new Translation2d(6.7, OFFSET - 3.25),
                new Translation2d(6.1, OFFSET - 3.5),
                new Translation2d(6.4, OFFSET - 4.2),
                new Translation2d(7.1, OFFSET - 3.9),
                new Translation2d(7.7, OFFSET - 3.6)
        ), new Pose2d(5, OFFSET - 0.75, Rotation2d.fromDegrees(0)));

        public static final Path FIFTH_PRIORITY_PATH_A = new Path(List.of(),
                new Pose2d(6.8, OFFSET - 3, Rotation2d.fromDegrees(315)));

        public static final Path FIFTH_PRIORITY_PATH_B = new Path(List.of(),
                new Pose2d(4, OFFSET - 0.75, Rotation2d.fromDegrees(315))).setReversed();

        public static final Path KABOOM_PATH = new Path(List.of(),
                new Pose2d(10.75, OFFSET - 0.75, Rotation2d.fromDegrees(0)));
    }
}

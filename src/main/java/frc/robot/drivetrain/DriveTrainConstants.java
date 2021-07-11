package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.constraint.CentripetalAccelerationConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.MaxVelocityConstraint;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.drivetrain.utils.Path;

import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.CONVERSION_RATE;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.ENCODER_UNITS;
import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargeStartPoints.SECOND_PRIORITY_PATH_START;

import java.util.List;

public final class DriveTrainConstants {

    public static final double WHEEL_DIAMETER_METER = Units.inchesToMeters(6);
    public static final double PERIMETER_METER = WHEEL_DIAMETER_METER * Math.PI;
    public static final double ARCADE_DRIVE_BUTTON_SENSITIVITY = 0.5; // TODO: check value
    public static final double ARCADE_DRIVE_SENSITIVITY = 0.8; // TODO: check value
    static final int DECISECOND_IN_SECOND = 10;
    static final int VOLTS = 12;
    static final double ARCADE_DRIVE_ROTATION_SENSITIVITY = 0.6; // TODO: check value

    public static final class DriveTrainSimConstantsA {
        static final double DRIVE_TRAIN_MASS = 54;
        static final double MOMENT_OF_INERTIA = 5.5;
    }

    public static final class DriveTrainComponentsA {

        static final int LEFT_MASTER_PORT = 3; // TODO: check value
        static final int LEFT_SLAVE_PORT = 4; // TODO: check value
        static final int RIGHT_MASTER_PORT = 1; // TODO: check value
        static final int RIGHT_SLAVE_PORT = 2; // TODO: check value
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
        public static final double ENCODER_CPR = ENCODER_UNITS * CONVERSION_RATE;
        public static final double MAX_SPEED_METERS_PER_SECOND = 2.5; // TODO: Calibration with A
        public static final double MAX_ACCELERATION_METERS_PER_SECOND_SQUARED = 3; // TODO: Calibration with A
        public static final double TRACKWIDTH_METERS = 0.7;
        public static final Pose2d START_POSE = SECOND_PRIORITY_PATH_START;
        private static final double KS = 0.738;
        private static final double KV = 2.08;
        private static final double KA = 0.321;
        public static final DifferentialDriveKinematics DRIVE_KINEMATICS =
                new DifferentialDriveKinematics(TRACKWIDTH_METERS);
        public static final SimpleMotorFeedforward FEEDFORWARD = new SimpleMotorFeedforward(KS, KV, KA);
    }

    public static final class InfiniteRechargeStartPoints {
        public static final Pose2d NEUTRAL_START = new Pose2d(0, 0 , Rotation2d.fromDegrees(0));
        public static final Pose2d PRIORITY_PATH_START = new Pose2d(3.05, 0.9 , Rotation2d.fromDegrees(0));
        public static final Pose2d SECOND_PRIORITY_PATH_START = new Pose2d(3.05, 7.5, Rotation2d.fromDegrees(0));
        public static final Pose2d THIRD_PRIORITY_PATH_START = new Pose2d(0, 0, Rotation2d.fromDegrees(0));
        public static final Pose2d FOURTH_PRIORITY_PATH_START = new Pose2d(3.05, 6.5, Rotation2d.fromDegrees(0));
        public static final Pose2d FIFTH_PRIORITY_PATH_START = new Pose2d(3.05, 6.5, Rotation2d.fromDegrees(0));
    }

    public static final class InfiniteRechargePaths {

        public static final Path PRIORITY_PATH_A = new Path(
                InfiniteRechargeStartPoints.PRIORITY_PATH_START,
                List.of(),
                new Pose2d(5.6, 0.9, Rotation2d.fromDegrees(0)),
                new CentripetalAccelerationConstraint(3),
                new MaxVelocityConstraint(1));

        public static final Path PRIORITY_PATH_B = new Path(
                PRIORITY_PATH_A.getEndPose(),
                List.of(),
                new Pose2d(5.2, 0.9, Rotation2d.fromDegrees(0)),
                new CentripetalAccelerationConstraint(3),
                new MaxVelocityConstraint(1)).setReversed();

        public static final Path PRIORITY_PATH_C = new Path(
                PRIORITY_PATH_B.getEndPose(),
                List.of(),
                new Pose2d(5.6, 0.5, Rotation2d.fromDegrees(0)),
                new CentripetalAccelerationConstraint(3),
                new MaxVelocityConstraint(1));

        public static final Path PRIORITY_PATH_D = new Path(
                PRIORITY_PATH_C.getEndPose(),
                List.of(
                        new Translation2d(4.6, 3.8)
                ),
                new Pose2d(4.3, 6, Rotation2d.fromDegrees(0)),
                new CentripetalAccelerationConstraint(2),
                new MaxVelocityConstraint(2)).setReversed();

        public static final Path SECOND_PRIORITY_PATH_A = new Path(
                InfiniteRechargeStartPoints.SECOND_PRIORITY_PATH_START,
                List.of(),
                new Pose2d(6.7, 7.5, Rotation2d.fromDegrees(0)),
                new CentripetalAccelerationConstraint(3),
                new MaxVelocityConstraint(3));

        public static final Path SECOND_PRIORITY_PATH_B = new Path(
                SECOND_PRIORITY_PATH_A.getEndPose(),
                List.of(),
                new Pose2d(9.6, 7.5, Rotation2d.fromDegrees(0)),
                new CentripetalAccelerationConstraint(3),
                new MaxVelocityConstraint(3));

        public static final Path SECOND_PRIORITY_PATH_C = new Path(
                SECOND_PRIORITY_PATH_B.getEndPose(),
                List.of(new Translation2d(7.5, 7.5)),
                new Pose2d(5.7, 7.1, Rotation2d.fromDegrees(30)),
                new CentripetalAccelerationConstraint(3),
                new MaxVelocityConstraint(3)).setReversed();

        public static final Path ONE_METER_FORWARD = new Path(
                new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
                List.of(),
                new Pose2d(1, 0, Rotation2d.fromDegrees(0)),
                new CentripetalAccelerationConstraint(2),
                new MaxVelocityConstraint(2));

        public static final Path THREE_BALLS_OUR_TRENCH_A = new Path(
                InfiniteRechargeStartPoints.SECOND_PRIORITY_PATH_START,
                List.of(),
                new Pose2d(6.7, 7.5, Rotation2d.fromDegrees(0)),
                new CentripetalAccelerationConstraint(2),
                new MaxVelocityConstraint(3));

        public static final Path THREE_BALLS_OUR_TRENCH_B = new Path(
                THREE_BALLS_OUR_TRENCH_A.getEndPose(),
                List.of(),
                new Pose2d(7.6, 7.5, Rotation2d.fromDegrees(0)),
                new CentripetalAccelerationConstraint(2),
                new MaxVelocityConstraint(3));

    }
}


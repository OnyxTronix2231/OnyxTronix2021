package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.util.Units;

import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.CONVERSION_RATE;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.ENCODER_UNITS;

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

        public static final double TRAJECTORY_P = 0.0002;
        public static final double RAMSETE_B = 2;
        public static final double RAMSETE_ZETA = 0.7;
        public static final double ENCODER_CPR = ENCODER_UNITS * CONVERSION_RATE; // TODO: Calibration with A
        public static final double MAX_SPEED_METERS_PER_SECOND = 5.5; // TODO: Calibration with A
        public static final double MAX_ACCELERATION_METERS_PER_SECOND_SQUARED = 6.9; // TODO: Calibration with A
        static final double TRACKWIDTH_METERS = 0.675; // TODO: Calibration with A
        private static final double kS = 0.480938; // TODO: This is Sim value, Calibration with A
        private static final double kV = 2.1073; // TODO: This is Sim value, Calibration with A
        private static final double kA = 0.3123; // TODO: Calibration with A
        public static final SimpleMotorFeedforward FEEDFORWARD = new SimpleMotorFeedforward(kS, kV, kA);
        public static final Pose2d START_POSE = new Pose2d(3.2, 0.7, Rotation2d.fromDegrees(0));
        public static final DifferentialDriveKinematics DRIVE_KINEMATICS =
                new DifferentialDriveKinematics(TRACKWIDTH_METERS);
    }
}

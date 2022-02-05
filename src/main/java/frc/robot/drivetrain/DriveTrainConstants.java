package frc.robot.drivetrain;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.trajectory.constraint.CentripetalAccelerationConstraint;
import edu.wpi.first.math.trajectory.constraint.MaxVelocityConstraint;

import java.util.List;

public final class DriveTrainConstants {
    public static final double MAX_OUTPUT_FORWARD = 1;
    public static final double MAX_OUTPUT_REVERSE = -1;
    public static final double CURRENT_LIMIT = 40;
    public static final double TRIGGER_THRESHOLD_CURRENT = 40;
    public static final double TRIGGER_THRESHOLD_TIME = 0.03;
    public static final double RAMP_TIME = 0.3;
    static final int DECISECOND_IN_SECOND = 10;
    static final int ENCODER_UNITS = 2048;
    static final int DECISECOND_PER_MINUTE = 600;
    static final double CONVERSION_RATE = 9;
    public static final double ENCODER_UNITS_TO_METER = 1;
    public static final double VOLTS = 12;
    public static final double WHEEL_DIAMETER_METER = 0.1524;
    public static final double PERIMETER_METER = WHEEL_DIAMETER_METER * Math.PI;
    static final int PIGEON_PORT = 0;
    static final double MAX_VELOCITY = 18000;
    static final double MAX_CLOSE_LOOP_OUTPUT = 1023;
    static final double kP = 0;
    static final double kI = 0;
    static final double kD = 0;
    static final double kF = MAX_CLOSE_LOOP_OUTPUT/MAX_VELOCITY;
    static final int acceleration = 0;
    static final int cruiseVelocity = 0;
    static final int accelerationSmoothing = 0;
    static final double tolerance = 0.5;

    public static final Pose2d TARGET_POSE = new Pose2d(2,2, new Rotation2d());

    public static final double TRAJECTORY_P = 0;
    public static final double ENCODER_UNITS_PER_ROTATION = ENCODER_UNITS * CONVERSION_RATE;
    public static final double MAX_SPEED_METERS_PER_SECOND = 4.5;
    public static final double MAX_ACCELERATION_METERS_PER_SECOND_SQUARED = 3;
    public static final double TRACKWIDTH_METERS = 0.69;
    private static final double KS = 0;
    private static final double KV = 0;
    private static final double KA = 0;
    public static final DifferentialDriveKinematics DRIVE_KINEMATICS =
            new DifferentialDriveKinematics(TRACKWIDTH_METERS);
    public static final SimpleMotorFeedforward FEEDFORWARD = new SimpleMotorFeedforward(KS, KV, KA);


    public static final class Calculations {

        static double encoderUnitsToMeter(double encodeUnits){
            return (encodeUnits / ENCODER_UNITS_PER_ROTATION) * WHEEL_DIAMETER_METER * Math.PI;
        }

        static double meterToEncoderUnits(double meters){
            return meters * ENCODER_UNITS_PER_ROTATION;
        }

    }

    public static final class Paths {

        public static final Path PATH_1 = new Path(
            new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
            List.of(new Translation2d(2, 0), new Translation2d(2,2)),
            new Pose2d(4, 2, Rotation2d.fromDegrees(0)),
            new CentripetalAccelerationConstraint(1),
            new MaxVelocityConstraint(1));

    }
}

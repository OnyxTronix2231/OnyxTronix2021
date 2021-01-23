package frc.robot.arc;

public class ArcConstants {

    static final int ANGULAR_MOTOR_CONVERSION = 1080; // 1/1080
    static final double ANGLE_PER_MOTOR_ROTATION = 360;
    static final double MAX_POSSIBLE_ANGLE = 70;
    static final double MIN_POSSIBLE_ANGLE = 20;
    static final double TOLERANCE_ANGLE = 3; // TODO: check and change
    static final double ENCODER_UNITS_PER_ROTATION = 2048;

    public static final class ArcConstantsA {
        static final int MASTER_MOTOR_ID = 5; // TODO: check and change
        static final double MAX_VELOCITY = 500; // Encoder Units // TODO: check and change
        static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
        static final int CRUISE_VELOCITY = 300; // Encoder Units // TODO: check and change
        static final int PEAK_AMP = 0; // TODO: check and change
        static final int MAX_ACCELERATION = 0; // TODO: check and change
        static final int ACCELERATION_SMOOTHING = 0; // 0 - 8
        static final int PEAK_AMP_DURATION = 0; // TODO: check and change
        static final int CONTINUOUS_CURRENT_LIMIT = 0; // TODO: check and change
        static final boolean CURRENT_LIMIT_ENABLED = false;
        static final double VELOCITY_P = 0.5; // TODO: check and change
        static final double VELOCITY_I = 0; // TODO: check and change
        static final double VELOCITY_D = 0; // TODO: check and change
        static final double VELOCITY_F = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
        static final double OPEN_LOOP_RAMP = 0; // TODO: check and change
        static final double CLOSE_LOOP_RAMP = 0; // TODO: check and change
    }
}

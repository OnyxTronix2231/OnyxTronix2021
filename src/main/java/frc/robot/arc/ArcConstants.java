package frc.robot.arc;

public class ArcConstants {

    public static final double MOVE_ARC_TO_SWITCH_LIMIT_SPEED = -0.25; // TODO: check and change
    public static final double MOVE_ARC_TO_MECHANISM_LIMIT_SPEED = -0.1; // TODO: check and change
    static final double ANGULAR_CONVERSION = 19.0/20;
    static final double ANGLE_PER_MOTOR_ROTATION = 33.882;
    static final double OFFSET = 20; // TODO: check and change
    static final double MAX_POSSIBLE_ANGLE = 67 - OFFSET; // TODO: check and change
    static final double MIN_POSSIBLE_ANGLE = 20 - OFFSET; // TODO: check and change
    static final double TOLERANCE_ANGLE = 0.5; // TODO: check and change
    static final double ENCODER_UNITS_PER_ROTATION = 4096 * ANGULAR_CONVERSION;
    static final double MOVING_TOLERANCE_ENCODER_UNITS = 3; // TODO: check and change

    public static final class ArcConstantsA {

        static final int MOTOR_ID = 5; // TODO: check and change
        static final int CRUISE_VELOCITY = 450; // Encoder Units // TODO: check and change
        static final int PEAK_AMP = 0; // TODO: check and change
        static final int MAX_ACCELERATION = 650; // TODO: check and change
        static final int ACCELERATION_SMOOTHING = 0; // 0 - 8
        static final int PEAK_AMP_DURATION = 0; // TODO: check and change
        static final int CONTINUOUS_CURRENT_LIMIT = 0; // TODO: check and change
        static final double MAX_VELOCITY = 550; // Encoder Units // TODO: check and change
        static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
        static final double KP = 1; // TODO: check and change
        static final double KI = 0; // TODO: check and change
        static final double KD = 0; // TODO: check and change
        static final double KF = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
        static final double OPEN_LOOP_RAMP = 0; // TODO: check and change
        static final double CLOSE_LOOP_RAMP = 0; // TODO: check and change
        static final boolean CURRENT_LIMIT_ENABLED = false; // TODO: check and change
    }
}

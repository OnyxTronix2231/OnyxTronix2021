package frc.robot.arc;

public class ArcConstants {

    public static final double MOVE_ARC_TO_SWITCH_LIMIT_SPEED = -0.25; // TODO: check and change
    public static final double DELAY_CALIBRATE_TIME = 0.5; // second
    static final double OFFSET = 21;
    public static final double MIN_POSSIBLE_ANGLE = 0;
    static final int MIDDLE_DISTANCE_ARC = 230;
    static final int TIME_OUT = 100;
    static final double START_ENCODER_VALUE = 150;
    static final double ANGULAR_CONVERSION = 19.0/20;
    static final double ANGLE_PER_MOTOR_ROTATION = 33.882;
    static final double MAX_POSSIBLE_ANGLE = 63 - OFFSET;
    static final double REAL_MAX_POSSIBLE_ANGLE = 63;
    static final double TOLERANCE_ANGLE = 0.5; // TODO: check and change
    static final double ENCODER_UNITS_PER_ROTATION = 4096 * ANGULAR_CONVERSION;

    public static final class ArcConstantsA {

        static final int MOTOR_ID = 5;
        static final int CRUISE_VELOCITY = 450; // Encoder Units
        static final int PEAK_AMP = 0; // TODO: check and change
        static final int MAX_ACCELERATION = 650;
        static final int ACCELERATION_SMOOTHING = 0; // 0 - 8
        static final int PEAK_AMP_DURATION = 0; // TODO: check and change
        static final int CONTINUOUS_CURRENT_LIMIT = 0; // TODO: check and change
        static final double MAX_VELOCITY = 500 * 1.2; // Encoder Units
        static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
        static final double KP = 8;
        static final double KI = 0.02;
        static final double KD = 0;
        static final double KF = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
        static final double OPEN_LOOP_RAMP = 0; // TODO: check and change
        static final double CLOSE_LOOP_RAMP = 0; // TODO: check and change
        static final boolean CURRENT_LIMIT_ENABLED = false; // TODO: check and change
    }

        public static final class ArcCalculation {

            static double FORMULA_DISTANCE_FAR(double distance) {
                return 0.00008 * Math.pow(distance, 2) - 0.0325 * distance + 51.521;
            }

            static double FORMULA_DISTANCE_CLOSE(double distance) { // TODO: change
                return 21;
            }
        }
}

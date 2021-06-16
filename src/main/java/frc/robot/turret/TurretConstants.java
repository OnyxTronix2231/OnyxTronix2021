package frc.robot.turret;

public class TurretConstants {

    static final int MASTER_MOTOR_ID = 8;
    static final int FLIP_POINT = 360;
    static final double TOLERANCE_DEGREE = 0.5;
    static final double TURRET_CONVERSION = 1;
    static final double ENCODER_UNITS_PER_ROUND = 1023 * TURRET_CONVERSION;
    static final double DEGREES_IN_CIRCLE = 360;
    static final double MAX_DEGREE = 225;
    static final double MIN_DEGREE = -135;
    static final int ENCODER_OFFSET = 336;

    public static final class TurretConstantsA {

        static final int MAX_ACCELERATION = 1000;
        static final int CRUISE_VELOCITY = 200;
        static final int ACCELERATION_SMOOTHING = 0;
        static final int CONTINUOUS_CURRENT_LIMIT = 0;
        static final int PEAK_AMP_DURATION = 0;
        static final int PEAK_AMP = 0;
        static final int SLOT_IDX = 0;
        static final double KP = 1;
        static final double KI = 0.005;
        static final double KD = 30;
        static final double KF = 1023 / 250.0;
        static final double CLOSE_LOOP_RAMP = 0;
        static final double OPEN_LOOP_RAMP = 0;
        static final double INTEGRAL_ZONE_BOUND = 30;
        static final boolean CURRENT_LIMIT_ENABLED = false;
    }
}

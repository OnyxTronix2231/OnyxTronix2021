package frc.robot.turret;

public class TurretConstants {

    static final int MASTER_MOTOR_ID = 1;
    static final int FLIP_POINT = 360;
    static final int TOLERANCE_DEGREE = 3;
    static final double TURRET_CONVERSION = 1;
    static final double ENCODER_UNITS_PER_ROUND = 1023 * TURRET_CONVERSION;
    static final double DEGREES_IN_CIRCLE = 360;
    static final double MAX_DEGREE = 225;
    static final double MIN_DEGREE = -135;

    public static final class TurretComponentsA {

        static final int PID_IDX = 0;
        static final int MAX_ACCELERATION = 0;
        static final int CRUISE_VELOCITY = 0;
        static final int ACCELERATION_SMOOTHING = 0;
        static final int PID_POSITION_GAINS = 0;
        static final int TIME_OUT_MS = 0;
        static final int CONTINUOUS_CURRENT_LIMIT = 0;
        static final int PEAK_AMP_DURATION = 0;
        static final int PEAK_AMP = 0 ;
        static final double KP = 0;
        static final double KI = 0;
        static final double KD = 0;
        static final double KF = 0;
        static final double CLOSE_LOOP_RAMP = 0;
        static final double OPEN_LOOP_RAMP = 0;
        static final boolean CURRENT_LIMIT_ENABLED = false;
    }
}

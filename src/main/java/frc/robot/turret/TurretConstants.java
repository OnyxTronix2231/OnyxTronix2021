package frc.robot.turret;

public class TurretConstants {
    static final int MASTER_MOTOR_ID = 1;
    static final double ENCODER_UNITS = 1023;
    static final double RATIO = 1;
    static final double DEGREES_IN_CIRCLE = 360;
    static final double MAX_DEGREE = 225;
    static final double MIN_DEGREE = -135;
    static final int TOLERANCE = 3;
    static final int FLIP_POINT = 360;
    public static final class TurretComponentsA{
        static final int PID_IDX = 0;
        static final int KP = 0;
        static final int KI = 0;
        static final int KD = 0;
        static final int KF = 0;
        static final int ACCELERATION = 0;
        static final int CRUISE_VELOCITY = 0;
        static final int ACCELERATION_SMOOTHING = 0;
        static final int PID_POSITION_GAINS= 0;
        static final int TIME_OUT_MS = 0;
    }
}

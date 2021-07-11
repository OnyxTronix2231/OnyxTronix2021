package frc.robot.revolver;

public final class RevolverConstants {

    public static final double UNCLOG_SPEED = -0.15;
    public static final double UNCLOG_CHECK_DELAY = 0.3;
    public static final double UNCLOG_TIME = 0.8;
    public static final double WAIT_REVOLVER = 1;
    public static final int REVOLVER_SLOW_SPIN_RPM = 30;
    static final int DECISECOND_IN_MIN = 600;
    static final int TOLERANCE_IN_RPM = 10; //TODO: check value
    private static final double CONVERSION_RATE = 26.5625; //26.5625:1
    static final double ENCODER_UNITS_PER_ROTATION = 2048 * CONVERSION_RATE; //TODO: check value
    static final double MINIMUM_RPM_FOR_CLOSE_LOOP_RAMP = 0;
    static final double CLOSE_LOOP_RAMP_WHILE_SHOOTING = 5;
    static final double REVOLVER_RPM_WHILE_SPINING = 30;

    static final double BALL_ONE_POS_FIRST = 6876;
    static final double BALL_ONE_POS_SECOND = 10812;

    static final double BALL_TWO_POS_FIRST = 17756;
    static final double BALL_TWO_POS_SECOND = 21692;

    static final double BALL_THIRD_POS_FIRST = 28636;
    static final double BALL_THIRD_POS_SECOND = 32572;

    static final double BALL_FOURTH_POS_FIRST = 39516;
    static final double BALL_FOURTH_POS_SECOND = 43452;

    static final double BALL_FIVE_POS_FIRST = 50396;
    static final double BALL_FIVE_POS_SECOND = 54332;

    public static final class RevolverComponentsA {

        static final int MASTER_MOTOR_ID = 11;
        static final int HALL_EFECT_PORT = 2;
        static final int SUPPLY_CURRENT_LIMIT = 10; // TODO: check value
        static final int SUPPLY_TRIGGER_THRESHOLD_TIME = 0; //TODO: check value
        static final int SUPPLY_TRIGGER_THRESHOLD_CURRENT = 0; //TODO: check value
        static final int STATOR_CURRENT_LIMIT = 0; //TODO: check value
        static final int STATOR_TRIGGER_THRESHOLD_TIME = 0; //TODO: check value
        static final int STATOR_TRIGGER_THRESHOLD_CURRENT = 0; //TODO: check value
        static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
        static final double MAX_VELOCITY = 17400 * 1.25; //TODO: check value
        static final double VELOCITY_P = 0.05; //TODO: check value
        static final double VELOCITY_I = 0.0002; //TODO: check value
        static final double VELOCITY_D = 6.5; //TODO: check value
        static final double VELOCITY_F = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
        static final double INTEGRAL_ZONE = 200;
        static final double OPEN_LOOP_RAMP = 0; //TODO: check value
        static final double PEAK_OUTPUT_FORWARD = 1; //TODO: check// value
        static final double PEAK_OUTPUT_REVERSE = -1; //TODO: check value
        static final double REGULAR_AMP = 3.5;
        static final boolean SUPPLY_CURRENT_LIMIT_ENABLED = true;
        static final boolean STATOR_CURRENT_LIMIT_ENABLED = false;
    }
}

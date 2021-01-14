package frc.robot.shooter;

public final class ShooterConstants {

    public static final class ShooterConstantsA { //TODO change

        static final int MASTER_MOTOR_ID = 7;
        static final int SLAVE_MOTOR_ID = 8;
        static final int CURRENT_LIMIT = 40;//TODO check and change
        static final double TRIGGER_THRESHOLD_TIME = 0.1;//TODO check and change
        static final int SHOOTER_MOTOR_MAX_VELOCITY = 19000; /** Encoder Units */
        static final int TRIGGER_THRESHOLD_CURRENT = 30; // TODO: check and change
        static final int PID_VELOCITY_GAINS = 0;
        static final double MAX_CLOSED_LOOP_OUTPUT = 1024;
        static final double SHOOTER_VELOCITY_P = 0.6;
        static final double SHOOTER_VELOCITY_I = 0;
        static final double SHOOTER_VELOCITY_D = 0;
        static final double SHOOTER_VELOCITY_F = MAX_CLOSED_LOOP_OUTPUT / SHOOTER_MOTOR_MAX_VELOCITY;
        static final double ANGLE_MOTOR_VELOCITY_P = 0.6;
        static final double ANGLE_MOTOR_VELOCITY_I = 0;
        static final double ANGLE_MOTOR_VELOCITY_D = 0;
        static final double ANGLE_VELOCITY_F = 0;
        static final int ANGLE_MOTOR_CRUISE_VELOCITY = 19000; /** Encoder Units */
        static final int ANGLE_MOTOR_MAX_ACCELERATION = 6000; /** Encoder Units */
        static final int ANGLE_MOTOR_ACCELERATION_SMOOTHING = 0; /** Encoder Units */
        static final double OPEN_LOOP_RAMP = 0;//TODO: check and change
        static final double CLOSE_LOOP_RAMP = 0;//3;//TODO: check and change
    }

    static final int TOLERANCE = 300;
    static final int MIDDLE_DISTANCE = 450;
    static final double ANGLE_TO_ENCODER_UNITS = 0; /** one encoder units spin to angle */
    static final double ENCODER_UNITS_PER_ROTATION = 2048;
    static final double MILLISECOND_TO_MINUTE = 600;
    static final double MIN_ERROR_RPM = 1000;
    static final double AT_SHOOTING_RPM = 400;
}

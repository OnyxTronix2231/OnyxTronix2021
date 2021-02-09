package frc.robot.ballTrigger;

public final class BallTriggerConstants {

    static final int ENCODER_UNITS_PER_ROTATION = 4096;
    static final int DECISECOND_IN_MIN = 600;
    static final boolean OPEN_PISTON = true;
    static final boolean CLOSE_PISTON = false;

    public static final class BallTriggerConstantsA {

        static final int MASTER_MOTOR_ID = 9; //TODO: check value
        static final int SOLENOID_CHANNEL = 1; //TODO: check value
        static final int PEAK_AMP = 0; //TODO: check value
        static final int PEAK_AMP_DURATION = 0; //TODO: check value
        static final int CONTINUOUS_CURRENT_LIMIT = 0; //TODO: check value
        static final int OPEN_LOOP_RAMP = 0; //TODO: check value
        static final int CLOSED_LOOP_RAMP = 0; //TODO: check value
        static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
        static final double MAX_VELOCITY = 0; //TODO: check value
        static final double VELOCITY_P = 0; //TODO: check value
        static final double VELOCITY_I = 0; //TODO: check value
        static final double VELOCITY_D = 0; //TODO: check value
        static final double VELOCITY_F = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
        static final boolean CURRENT_LIMIT_ENABLED = false;
    }
}

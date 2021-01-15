package frc.robot.revolver;

public final class RevolverConstants {

    static final int ENCODER_UNITS_PER_ROTATION = 1023;
    static final int SECONDS_IN_MIN = 60;
    static final int HUNDREDS_OF_MILLISECS_IN_SEC = 10;

    public static final class RevolverComponentsA {
        static final int MASTER_MOTOR_ID = 9; //TODO: check value
        static final int TOLERANCE = 3;
        static final double VELOCITY_P = 0; //TODO: check value
        static final double VELOCITY_I = 0; //TODO: check value
        static final double VELOCITY_D = 0; //TODO: check value
        static final double VELOCITY_F = 0; //TODO: check value
        static final double MAX_CLOSED_LOOP_OUTPUT = 1023; //TODO: check value
        static final double MAX_RPM = 1500; //TODO: check value
    }
}

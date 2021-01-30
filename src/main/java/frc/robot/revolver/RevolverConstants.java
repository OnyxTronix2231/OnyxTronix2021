package frc.robot.revolver;

public final class RevolverConstants {

    static final int DECISECONDS_IN_MIN = 600;
    static final int TOLERANCE_IN_RPM = 3; //TODO: check value
    static final double CONVERSION_RATE = 26.5625; //1:26.5625
    static final double ENCODER_UNITS_PER_ROTATION = 2048 * CONVERSION_RATE; //TODO: check value

    public static final class RevolverComponentsA {
        static final int MASTER_MOTOR_ID = 9; //TODO: check value
        static final int SUPPLY_CURRENT_LIMIT = 0; //TODO: check value
        static final int SUPPLY_TRIGGER_THRESHOLD_TIME = 0; //TODO: check value
        static final int SUPPLY_TRIGGER_THRESHOLD_CURRENT = 0; //TODO: check value
        static final int STATOR_CURRENT_LIMIT = 0; //TODO: check value
        static final int STATOR_TRIGGER_THRESHOLD_TIME = 0; //TODO: check value
        static final int STATOR_TRIGGER_THRESHOLD_CURRENT = 0; //TODO: check value
        static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
        static final double MAX_ENCODER_UNITS = 2400; //TODO: check value
        static final double VELOCITY_P = 0; //TODO: check value
        static final double VELOCITY_I = 0; //TODO: check value
        static final double VELOCITY_D = 0; //TODO: check value
        static final double VELOCITY_F = MAX_CLOSED_LOOP_OUTPUT / MAX_ENCODER_UNITS;
        static final double OPEN_LOOP_RAMP = 0; //TODO: check value
        static final double CLOSED_LOOP_RAMP = 0; //TODO: check value
        static final double PEAK_OUTPUT_FORWARD = 1; //TODO: check value
        static final double PEAK_OUTPUT_REVERSE = -1; //TODO: check value
        static final boolean SUPPLY_CURRENT_LIMIT_ENABLED = true;
        static final boolean STATOR_CURRENT_LIMIT_ENABLED = true;
    }
}
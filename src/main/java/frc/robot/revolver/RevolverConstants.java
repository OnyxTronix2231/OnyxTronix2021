package frc.robot.revolver;

public final class RevolverConstants {

    static final int ENCODER_UNITS_PER_ROTATION = 4096; //TODO: check value
    static final int HUNDREDS_OF_MILLISECS_IN_MIN = 600;
    static final int TOLERANCE_IN_RPM = 3; //TODO: check value

    public static final class RevolverComponentsA {
        static final int MASTER_MOTOR_ID = 9; //TODO: check value
        static final int PID_SLOT = 0;
        static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
        static final double MAX_RPM = 1500; //TODO: check value
        static final double VELOCITY_P = 0; //TODO: check value
        static final double VELOCITY_I = 0; //TODO: check value
        static final double VELOCITY_D = 0; //TODO: check value
        static final double VELOCITY_F = MAX_CLOSED_LOOP_OUTPUT / MAX_RPM; //TODO: check value
        static final int SUPPLY_CURRENT_LIMIT = 0; //TODO: check value
        static final int SUPPLY_TRIGGER_THRESHOLD_TIME = 0; //TODO: check value
        static final int SUPPLY_TRIGGER_THRESHOLD_CURRENT = 0; //TODO: check value
        static final int STATOR_CURRENT_LIMIT = 0; //TODO: check value
        static final int STATOR_TRIGGER_THRESHOLD_TIME = 0; //TODO: check value
        static final int STATOR_TRIGGER_THRESHOLD_CURRENT = 0; //TODO: check value
        static final double OPEN_LOOP_RAMP = 0; //TODO: check value
        static final double CLOSED_LOOP_RAMP = 0; //TODO: check value
        static final double PEAK_OUTPUT_FORWARD = 1; //TODO: check value
        static final double PEAK_OUTPUT_REVERSE = -1; //TODO: check value
        static final boolean SUPPLY_CURRENT_LIMIT_ENABLED = true;
        static final boolean SUPPLY_CURRENT_LIMIT_DISABLED = false;
        static final boolean STATOR_CURRENT_LIMIT_ENABLED = true;
        static final boolean STATOR_CURRENT_LIMIT_DISABLED = false;
        public static final double RPM_WHILE_COLLECTING = 1394.53125;
        public static final double RPM_WHILE_SHOOTING = 3187.5;
    }
}

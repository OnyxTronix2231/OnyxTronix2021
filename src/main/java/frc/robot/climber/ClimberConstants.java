package frc.robot.climber;

public class ClimberConstants {

    static final double CONVERSION_RATE = 1; //TODO: check value
    static final double ENCODER_UNITS_PER_ROTATION = 4090 * CONVERSION_RATE;
    static final double DISTANCE_PER_MOTOR_ROTATION = 0; //TODO: check value

    public static class ClimberConstantsA {

        static final int MASTER_MOTOR_ID = 14;
        static final int SLAVE_MOTOR_ID = 15;
        static final int ACCELERATION = 0; // Todo: ValueCheck
        static final int CRUISE_VELOCITY = 0; // Todo: ValueCheck
        static final int ACCELERATION_SMOOTHING = 0; // Todo: ValueCheck
        static final double PEAK_OUTPUT_FORWARD = 1;
        static final double PEAK_OUTPUT_REVERSE = -1;
        static final double SUPPLY_CURRENT_LIMIT = 0;
        static final double SUPPLY_TRIGGER_THRESHOLD_CURRENT = 0;
        static final double SUPPLY_TRIGGER_THRESHOLD_TIME = 0;
        static final double STATOR_CURRENT_LIMIT = 0;
        static final double STATOR_TRIGGER_THRESHOLD_CURRENT = 0;
        static final double STATOR_TRIGGER_THRESHOLD_TIME = 0;
        static final double CLOSED_LOOP_RAMP = 0;
        static final double OPEN_LOOP_RAMP = 0;
        static final double TOLERANCE = 0.2; // Todo: ValueCheck
        static final double TESTING_SPEED = 0.2; // Todo: ValueCheck
        static final double DISTANCE = 18000; // Todo: ValueCheck
        static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
        static final double MAX_ENCODER_UNITS = 4096; // Todo: ValueCheck
        static final double KP = 0; // Todo: ValueCheck
        static final double KI = 0; // Todo: ValueCheck
        static final double KD = 0; // Todo: ValueCheck
        static final double KF = MAX_CLOSED_LOOP_OUTPUT / MAX_ENCODER_UNITS;
        static final boolean SUPPLY_CURRENT_LIMIT_ENABLED = false;
        static final boolean STATOR_CURRENT_LIMIT_ENABLED = false;
    }
}

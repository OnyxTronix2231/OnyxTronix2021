package frc.robot.shooter;

public final class ShooterConstants {

    static final int MIDDLE_DISTANCE = 230;
    static final double SHOOTER_CONVERSION = 1.03;
    static final double ENCODER_UNITS_PER_ROTATION = 2048 * SHOOTER_CONVERSION;
    static final double DECISECOND_IN_MIN = 600;
    static final double MIN_ERROR_RPM = 1000;
    static final double TOLERANCE_RPM = 50;

    public static final class ShooterConstantsA {

        static final int MASTER_MOTOR_ID = 13;
        static final int SLAVE_MOTOR_ID = 7;
        static final int SUPPLY_CURRENT_LIMIT = 40; // TODO: check and change
        static final int SUPPLY_TRIGGER_THRESHOLD_CURRENT = 40; // TODO: check and change
        static final int STATOR_CURRENT_LIMIT = 0; // TODO: check and change
        static final int STATOR_TRIGGER_THRESHOLD_TIME = 0; // TODO: check and change
        static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
        static final double MAX_VELOCITY = 17750 * 1.2;
        static final double SUPPLY_TRIGGER_THRESHOLD_TIME = 1; // TODO: check and change
        static final double STATOR_TRIGGER_THRESHOLD_CURRENT = 0; // TODO: check and change
        static final double VELOCITY_P = 0.8;
        static final double VELOCITY_I = 0;
        static final double VELOCITY_D = 20;
        static final double VELOCITY_F = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
        static final double OPEN_LOOP_RAMP = 0; // TODO: check and change
        static final double CLOSE_LOOP_RAMP = 0; // TODO: check and change
        static final boolean SUPPLY_CURRENT_LIMIT_ENABLED = true; // TODO: check and change
        static final boolean STATOR_CURRENT_LIMIT_ENABLED = false; // TODO: check and change
    }

    public static final class ShooterCalculation { // TODO: change

        static double FORMULA(double distance) {
            return 0.0019 * Math.pow(distance, 2) + 2.0558 * distance + 2884.8;
            // old formula 0.0038 * Math.pow(distance, 2) + 0.677 * distance + 3119.2;
        }
    }
}

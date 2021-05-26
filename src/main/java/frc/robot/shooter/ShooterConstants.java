package frc.robot.shooter;

public final class ShooterConstants {

    static final int MIDDLE_DISTANCE = 450;
    static final double SHOOTER_CONVERSION = 1.03;
    static final double ENCODER_UNITS_PER_ROTATION = 2048 * SHOOTER_CONVERSION; //TODO: check
    static final double DECISECOND_IN_MIN = 600;
    static final double MIN_ERROR_RPM = 1000;
    static final double TOLERANCE_RPM = 400;

    public static final class ShooterConstantsA {

        static final int MASTER_MOTOR_ID = 13; // TODO: check and change
        static final int SLAVE_MOTOR_ID = 7; // TODO: check and change
        static final int SUPPLY_CURRENT_LIMIT = 0; // TODO: check and change
        static final int SUPPLY_TRIGGER_THRESHOLD_CURRENT = 0; // TODO: check and change
        static final int STATOR_CURRENT_LIMIT = 0; // TODO: check and change
        static final int STATOR_TRIGGER_THRESHOLD_TIME = 0; // TODO: check and change
        static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
        static final double MAX_VELOCITY = 17500; // TODO: check and change
        static final double SUPPLY_TRIGGER_THRESHOLD_TIME = 0; // TODO: check and change
        static final double STATOR_TRIGGER_THRESHOLD_CURRENT = 0; // TODO: check and change
        static final double VELOCITY_P = 0; // TODO: check and change
        static final double VELOCITY_I = 0; // TODO: check and change
        static final double VELOCITY_D = 0; // TODO: check and change
        static final double VELOCITY_F = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
        static final double OPEN_LOOP_RAMP = 0; // TODO: check and change
        static final double CLOSE_LOOP_RAMP = 0; // TODO: check and change
        static final boolean SUPPLY_CURRENT_LIMIT_ENABLED = false; // TODO: check and change
        static final boolean STATOR_CURRENT_LIMIT_ENABLED = false; // TODO: check and change
    }

    public static final class ShooterCalculation { // TODO: change

        static double FORMULA_DISTANCE_FAR(double distance) {
            System.err.println("there is no formula");
            return 0;
        }

        static double FORMULA_DISTANCE_CLOSE(double distance) { // TODO: change
            System.err.println("there is no formula");
            return 0;
        }
    }
}

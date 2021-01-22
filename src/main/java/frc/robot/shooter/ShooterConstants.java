package frc.robot.shooter;

public final class ShooterConstants {

    static final int MIDDLE_DISTANCE = 450;
    static final double ENCODER_UNITS_PER_ROTATION = 2048;
    static final double MILLISECOND_TO_MINUTE = 600;
    static final double MIN_ERROR_RPM = 1000;
    static final double TOLERANCE_RPM = 400;

    public static final class ShooterConstantsA {

        static final int MASTER_MOTOR_ID = 7; // TODO: check and change
        static final int SLAVE_MOTOR_ID = 8; // TODO: check and change
        static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
        static final double MAX_VELOCITY = 17500 * 1.2; // Encoder Units *80% // TODO: check and change
        static final int SUPPLY_CURRENT_LIMIT = 0; // TODO: check and change
        static final int SUPPLY_TRIGGER_THRESHOLD_CURRENT = 0; // TODO: check and change
        static final double SUPPLY_TRIGGER_THRESHOLD_TIME = 0; // TODO: check and change
        static final boolean ENABLE_SUPPLY_CURRENT_LIMIT = false;
        static final int STATOR_CURRENT_LIMIT = 0; // TODO: check and change
        static final double STATOR_TRIGGER_THRESHOLD_CURRENT = 0; // TODO: check and change
        static final int STATOR_TRIGGER_THRESHOLD_TIME = 0; // TODO: check and change
        static final boolean ENABLE_STATOR_CURRENT_LIMIT = false;
        static final double SHOOTER_VELOCITY_P = 0.3; // TODO: check and change
        static final double SHOOTER_VELOCITY_I = 0; // TODO: check and change
        static final double SHOOTER_VELOCITY_D = 0.1; // TODO: check and change
        static final double SHOOTER_VELOCITY_F = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
        static final double OPEN_LOOP_RAMP = 0; // TODO: check and change
        static final double CLOSE_LOOP_RAMP = 0; // TODO: check and change
    }

    // y = -0.0121x2 +26.707x + 24130 > 450
    // y = 0.1912x2 - 161.44x +67791 < 450
    public static final class ShooterCalculation {
        static double SHOOTER_FORMULA_FAR(double distance) {
            return -0.0121 * Math.pow(distance, 2) + 26.707 * distance + 24130;
        }

        static double SHOOTER_FORMULA_CLOSE(double distance) {
            return 0.1912 * Math.pow(distance, 2) - 161.44 * distance + 67791;
        }
    }
}

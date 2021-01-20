package frc.robot.shooter;

public final class ShooterConstants {

    public static final class ShooterConstantsA { //TODO: check all values on robot

        static final int MASTER_SHOOTER_MOTOR_ID = 7;
        static final int SLAVE_SHOOTER_MOTOR_ID = 8;
        static final int ANGULAR_MOTOR_ID = 5;
        static final int CURRENT_LIMIT = 40;
        static final int STATOR_CURRENT_LIMIT = 40;
        static final int TRIGGER_THRESHOLD_CURRENT = 30;
        static final int STATOR_TRIGGER_THRESHOLD_TIME = 30;
        static final int ANGULAR_MOTOR_MAX_ACCELERATION = 10;
        static final int ANGULAR_MOTOR_CRUISE_VELOCITY = 300; // Encoder Units
        static final int ANGULAR_MOTOR_ACCELERATION_SMOOTHING = 0; // 0 - 8
        static final int ANGULAR_MOTOR_PEAK_AMP_DURATION = 0;
        static final int ANGULAR_MOTOR_PEAK_AMP = 20;
        static final int ANGULAR_MOTOR_CONTINUOUS_CURRENT_LIMIT = 35;
        static final double TRIGGER_THRESHOLD_TIME = 0.1;
        static final double STATOR_TRIGGER_THRESHOLD_CURRENT = 0.1;
        static final double SHOOTER_MOTOR_MAX_VELOCITY = 17500 * 1.2; // Encoder Units *80%
        static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
        static final double SHOOTER_VELOCITY_P = 0.3;
        static final double SHOOTER_VELOCITY_I = 0;
        static final double SHOOTER_VELOCITY_D = 0.1;
        static final double SHOOTER_VELOCITY_F = MAX_CLOSED_LOOP_OUTPUT  / SHOOTER_MOTOR_MAX_VELOCITY;
        static final double SHOOTER_MOTOR_OPEN_LOOP_RAMP = 0;
        static final double ANGULAR_MOTOR_VELOCITY_P = 0.5;
        static final double ANGULAR_MOTOR_VELOCITY_I = 0;
        static final double ANGULAR_MOTOR_VELOCITY_D = 0;
        static final double ANGULAR_MOTOR_VELOCITY_F = MAX_CLOSED_LOOP_OUTPUT / ANGULAR_MOTOR_CRUISE_VELOCITY;
        static final double SHOOTER_MOTOR_CLOSE_LOOP_RAMP = 0;
        static final double ANGULAR_MOTOR_OPEN_LOOP_RAMP = 1;
        static final double ANGULAR_MOTOR_CLOSE_LOOP_RAMP = 0;
    }

    // y = -0.0121x2 +26.707x + 24130 > 450
    // y = 0.1912x2 - 161.44x +67791 < 450
    public static final class ShooterCalculation{
        static double SHOOTER_FORMULA_FAR(double distance){
            return -0.0121 * Math.pow(distance, 2) + 26.707 * distance + 24130;
        }
        static double SHOOTER_FORMULA_CLOSE(double distance){
            return 0.1912 * Math.pow(distance, 2) - 161.44 * distance + 67791;
        }
    }

    static final int MIDDLE_DISTANCE = 450;
    static final int ANGULAR_MOTOR_CONVERSION = 1080; // 1/1080
    static final double ANGLE_PER_ROTATION = 360; // one encoder units spin to angle
    static final double MAX_POSSIBLE_ANGLE = 70;
    static final double MIN_POSSIBLE_ANGLE = 20;
    static final double ENCODER_UNITS_PER_ROTATION = 2048;
    static final double MILLISECOND_TO_MINUTE = 600;
    static final double MIN_ERROR_RPM = 1000;
    static final double SHOOTER_TOLERANCE_RPM = 400; //RPM
    static final double ANGULAR_TOLERANCE_DEGREE = 3; //degree
}

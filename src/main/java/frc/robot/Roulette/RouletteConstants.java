package frc.robot.Roulette;

public class RouletteConstants {

    public static final double ROULETTE_ROUNDS_TOLERANCE = 0.1; // TODO: DECIDE TO CHANGE VALUE?
    public static final RouletteColor ROULETTE_RED = new RouletteColor(1, 0, 0, "Red"); //TODO: check value again irl
    public static final RouletteColor ROULETTE_GREEN = new RouletteColor(0, 1, 0, "Green"); //TODO: check value again irl
    public static final RouletteColor ROULETTE_BLUE = new RouletteColor(0, 1, 1, "Blue"); //TODO: check value again irl
    public static final RouletteColor ROULETTE_YELLOW = new RouletteColor(1, 1, 0, "Yellow"); //TODO: check value again irl
    static final int MASTER_MOTOR_ID = 10;
    static final int SOLENOID_ID = 2;
    static final int ENCODER_UNITS_PER_WHEEL_ROUND = 4096;
    static final double WHEEL_CIRCUMFERENCE = 0.1595929068;// in meters
    static final double RATIO_ROULETTE_TO_WHEEL = 2.54 / WHEEL_CIRCUMFERENCE;
    static final RouletteColor[] ROULETTE_COLORS = {ROULETTE_RED, ROULETTE_YELLOW, ROULETTE_BLUE, ROULETTE_GREEN};
    static final double RATIO_ROULETTE_TO_ROULETTE_COLOR = 1.0 / 8;
    static final double PERCENT_TOLERANCE = 0.05; //TODO: check real value
    static final int REQUIRED_AMOUNT_OF_ROUNDS = 3;
    static final int COLOR_OFFSET = 2;
    static final int MAX_COLOR_INDEX = 3;
    static final int MIN_COLOR_INDEX = 0;
    static final int INEFFICIENT_DISTANCE = 3;
    static final int OPTIMIZED_DISTANCE = 1;

    static class RouletteConstantsA {

        static final double KP = 0; //TODO: check real value
        static final double KI = 0; //TODO: check real value
        static final double KD = 0; //TODO: check real value
        static final double KF = 0; //TODO: check real value
        static final int MAX_ACCELERATION = 0; //TODO: check real value
        static final int MAX_CRUISE_VELOCITY = 0; //TODO: check real value
        static final int ACCELERATION_SMOOTHING = 0; //TODO: check real value
    }
}

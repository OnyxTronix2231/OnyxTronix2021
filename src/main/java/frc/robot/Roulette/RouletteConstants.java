package frc.robot.Roulette;

import edu.wpi.first.wpilibj.util.Color;

public class RouletteConstants {

    static final int MASTER_MOTOR_ID = 0; //TODO: check real value
    static final int SOLENOID_ID = 0; // TODO: check real value
    static final int ENCODER_UNITS_PER_WHEEL_ROUND = 4096;
    static final double WHEEL_CIRCUMFERENCE = 0.15959;// in meters // TODO: check real value
    static final double RATIO_ROULETTE_TO_WHEEL = 2.54 / WHEEL_CIRCUMFERENCE; //TODO: check real value
    static final RouletteColor ROULETTE_RED = new RouletteColor(1, 0, 0, "Red"); //TODO: check value again irl
    static final RouletteColor ROULETTE_GREEN = new RouletteColor(0.4, 1, 0.8, "Green"); //TODO: check value again irl
    static final RouletteColor ROULETTE_BLUE = new RouletteColor(0, 0.8, 0.8, "Blue"); //TODO: check value again irl
    static final RouletteColor ROULETTE_YELLOW = new RouletteColor(.55, 0.9, 0, "Yellow"); //TODO: check value again irl
    static final RouletteColor[] ROULETTE_COLORS = {ROULETTE_RED, ROULETTE_YELLOW, ROULETTE_BLUE, ROULETTE_GREEN};
    static final int NUM_OF_RGB_CHARS = 3;
    static final double RATIO_ROULETTE_TO_ROULETTE_COLOR = 1.0 / 8;
    static final double PERCENT_TOLERANCE = 0.05; //TODO: check real value
    public static final double ROULETTE_ROUNDS_TOLERANCE = 0.1; // TODO: DECIDE TO CHANGE VALUE?
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
        static final double KF = 1023 / 15645.8525; //TODO: check real value
        static final int MAX_ACCELERATION = 110; //TODO: check real value
        static final int MAX_VELOCITY = 15000; //TODO: check real value
        static final int ACCELERATION_SMOOTHING = 0; //TODO: check real value
        static final double CRUISE_VELOCITY_DEFAULT_VALUE = 0.0;
        static final double ACCELERATION_SMOOTHING_DEFAULT_VALUE = 0.0;
        static final double ACCELERATION_DEFAULT_VALUE = 0.0;
    }
}

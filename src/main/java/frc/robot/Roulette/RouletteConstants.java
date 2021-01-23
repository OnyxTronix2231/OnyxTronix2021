package frc.robot.Roulette;

import edu.wpi.first.wpilibj.util.Color;

public class RouletteConstants {

    static final int DEVICE_NUMBER = 0; // TODO: check real value
    static final int CHANNEL = 0; // TODO: check real value
    static final int ENCODER_UNITS_PER_WHEEL_ROUND = 4096;
    static final double WHEEL_CIRCUMFERENCE = 0.08;// in meters // TODO: check real value
    static final double RATIO_ROULETTE_TO_WHEEL = 12; //TODO: check real value
    static final RouletteColor ROULETTE_RED = new RouletteColor(1, 0, 0, "Red"); //TODO: check value again irl
    static final RouletteColor ROULETTE_GREEN = new RouletteColor(0.4, 1, 0.8, "Green"); //TODO: check value again irl
    static final RouletteColor ROULETTE_BLUE = new RouletteColor(0, 0.8, 0.8, "Blue"); //TODO: check value again irl
    static final RouletteColor ROULETTE_YELLOW = new RouletteColor(.55, 0.9, 0, "Yellow"); //TODO: check value again irl
    static final RouletteColor[] ROULETTE_COLORS = {ROULETTE_RED, ROULETTE_YELLOW, ROULETTE_BLUE, ROULETTE_GREEN};
    static final double RATIO_ROULETTE_TO_ROULETTE_COLOR = 1.0 / 8;
    static final double PERCENT_TOLERANCE = 0.05; //TODO: check real value
    static final int REQUIRED_AMOUNT_OF_ROUNDS = 3;
    static final int COLOR_OFFSET = 2;
    static final int MAX_COLOR_INDEX = 3;
    static final int MIN_COLOR_INDEX = 0;
    static final int INEFFICIENT_DISTANCE = 3;
    static final int OPTIMIZED_DISTANCE = 1;

    static class CollectorConstantsA {
        final static double K_P = 0; //TODO: check real value
        final static double K_I = 0; //TODO: check real value
        final static double K_D = 0; //TODO: check real value
        final static double K_F = 0; //TODO: check real value
        final static int MAX_ACCELERATION = 0; //TODO: check real value
        final static int MAX_VELOCITY = 0; //TODO: check real value
        final static int ACCELERATION_SMOOTHING = 0; //TODO: check real value
    }

}

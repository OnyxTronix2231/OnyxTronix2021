package frc.robot.Roulette;

import edu.wpi.first.wpilibj.util.Color;

public class RouletteConstants {

    static final int DEVICE_NUMBER = 0; // TODO: check real value
    static final int CHANNEL = 0; // TODO: check real value
    static final int ENCODER_UNITS_PER_WHEEL_ROUND = 4096;
    static final double WHEEL_CIRCUMFERENCE = 0.08;// in meters // TODO: check real value
    static final double RATIO_ROULETTE_TO_WHEEL = 12; //TODO: check real value
    static final RouletteColor ROULETTE_RED = new RouletteColor(new Color(255, 0, 0)); //TODO: check value again irl
    static final RouletteColor ROULETTE_GREEN = new RouletteColor(new Color(100, 255, 200)); //TODO: check value again irl
    static final RouletteColor ROULETTE_BLUE = new RouletteColor(new Color(0, 200, 200)); //TODO: check value again irl
    static final RouletteColor ROULETTE_YELLOW = new RouletteColor(new Color(150, 200, 0)); //TODO: check value again irl
    static final RouletteColor[] ROULETTE_COLORS = {ROULETTE_RED, ROULETTE_YELLOW, ROULETTE_BLUE, ROULETTE_GREEN};
    static final double RATIO_ROULETTE_TO_ROULETTE_COLOR = 1.0 / 8;
    static final double PERCENT_TOLERANCE = 0.05; //TODO: check real value
    static final int REQUIRED_AMOUNT_OF_ROUNDS = 3;

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

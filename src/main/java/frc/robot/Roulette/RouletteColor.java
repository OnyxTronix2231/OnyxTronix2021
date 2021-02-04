package frc.robot.Roulette;

import edu.wpi.first.wpilibj.util.Color;

import static frc.robot.Roulette.RouletteConstants.NUM_OF_RGB_CHARS;

public class RouletteColor extends Color {

    private String name;

    public RouletteColor(double red, double green, double blue, String name) {
        super(red, green, blue);
        this.name = name;
    }

    public double howCloseTo(Color color) {
        double rgbPercentage = (Math.abs(red - color.red) +
                Math.abs(green - color.green) + Math.abs(blue - color.blue)) / NUM_OF_RGB_CHARS;
        return 1 - rgbPercentage;
    }

    public String getName() {
        return name;
    }
}

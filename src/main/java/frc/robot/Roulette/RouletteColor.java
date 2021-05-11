package frc.robot.Roulette;

import edu.wpi.first.wpilibj.util.Color;

public class RouletteColor extends Color {

    private final String name;

    public RouletteColor(double red, double green, double blue, String name) {
        super(red, green, blue);
        this.name = name;
    }

    public double howCloseTo(Color color) {
        double rgbPercentage = (Math.abs(red - color.red) +
                Math.abs(green - color.green) + Math.abs(blue - color.blue)) / 3;
        return 1 - rgbPercentage;
    }

    public String getName() {
        return name;
    }
}

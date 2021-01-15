package frc.robot.Roulette;

import edu.wpi.first.wpilibj.util.Color;

public class RouletteColor {

    private Color color;

    public RouletteColor(Color color) {
        this.color = color;
    }

    public double howCloseTo(RouletteColor rouletteColor) {
        double red1 = color.red / 255;
        double green1 = color.green / 255;
        double blue1 = color.blue / 255;
        double red2 = rouletteColor.color.red / 255;
        double green2 = rouletteColor.color.green / 255;
        double blue2 = rouletteColor.color.blue / 255;
        double rgbPercentage = (Math.abs(red1 - red2) +
                Math.abs(green1 - green2) + Math.abs(blue1 - blue2)) / 3;
        return 1 - rgbPercentage;
    }
}

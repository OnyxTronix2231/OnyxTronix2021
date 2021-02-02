package frc.robot.Roulette.commands;

import frc.robot.Roulette.Roulette;
import frc.robot.Roulette.RouletteColor;

import java.util.function.Supplier;

public class SpinToColor extends SpinByRouletteRounds {

    public SpinToColor(Roulette roulette, Supplier<RouletteColor> colorSupplier) {
        super(roulette, () -> roulette.getRoundsToColor(colorSupplier.get()));
    }
}

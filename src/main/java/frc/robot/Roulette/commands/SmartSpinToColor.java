package frc.robot.Roulette.commands;

import frc.robot.Roulette.Roulette;
import frc.robot.Roulette.RouletteColor;

import java.util.function.Supplier;

public class SmartSpinToColor extends SmartSpinByRouletteRounds {

    public SmartSpinToColor(Roulette roulette, Supplier<RouletteColor> colorSupplier) {
        super(roulette, () -> roulette.getRoundsToColor(colorSupplier.get()));
    }
}

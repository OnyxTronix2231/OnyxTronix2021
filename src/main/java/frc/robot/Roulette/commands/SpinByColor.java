package frc.robot.Roulette.commands;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Roulette.Roulette;

import javax.swing.*;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class SpinByColor extends SpinByRouletteRounds {

    private Supplier<Color> colorSupplier;
    private Color prevColor;

    public SpinByColor(Roulette roulette, Supplier<Color> colorSupplier) {
        super(roulette, roulette.remainingRouletteRounds()); //? - second parameter, what do
        this.colorSupplier = colorSupplier;
    }


    @Override
    public void initialize() {
        super.initialize();
        SpinByRouletteRounds.
    }

    @Override
    public void execute() {
        super.execute();
    }

}

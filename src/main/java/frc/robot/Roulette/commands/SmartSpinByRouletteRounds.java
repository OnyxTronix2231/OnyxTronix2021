package frc.robot.Roulette.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Roulette.Roulette;
import frc.robot.Roulette.RouletteColor;

import java.util.function.DoubleSupplier;

public class SmartSpinByRouletteRounds extends CommandBase {

    private final Roulette roulette;
    private DoubleSupplier rouletteRounds;
    private final SpinByRouletteRounds spinByRouletteRounds;
    private RouletteColor currentColor;
    private int counter;

    public SmartSpinByRouletteRounds(Roulette roulette, DoubleSupplier rouletteRounds) {
        this.roulette = roulette;
        this.rouletteRounds = rouletteRounds;
        spinByRouletteRounds = new SpinByRouletteRounds(roulette, () -> this.rouletteRounds.getAsDouble());
    }

    @Override
    public void initialize() {
        spinByRouletteRounds.initialize();
        currentColor = roulette.getCurrentColor();
        counter = 0;
    }

    @Override
    public void execute() {
        spinByRouletteRounds.execute();
        if (currentColor != roulette.getCurrentColor()) {
            counter++;
            currentColor = roulette.getCurrentColor();
        }
        if (spinByRouletteRounds.isFinished()) {
            double lastRouletteRounds = rouletteRounds.getAsDouble();
            rouletteRounds = () -> lastRouletteRounds - roulette.colorCountToRouletteRounds(counter);
            spinByRouletteRounds.initialize();
            counter = 0;
        }
    }

    @Override
    public boolean isFinished() {
        return Math.abs(rouletteRounds.getAsDouble() - roulette.colorCountToRouletteRounds(counter)) < 0.1;
    }

    @Override
    public void end(boolean interrupted) {
        spinByRouletteRounds.end(interrupted);
    }
}

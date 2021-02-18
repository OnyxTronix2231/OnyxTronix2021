package frc.robot.Roulette.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Roulette.Roulette;
import frc.robot.Roulette.RouletteColor;
import java.util.function.DoubleSupplier;

import static frc.robot.Roulette.RouletteConstants.ROULETTE_ROUNDS_TOLERANCE;

public class SmartSpinByRouletteRounds extends CommandBase {

    private Roulette roulette;
    private DoubleSupplier rouletteRounds;
    private SpinByRouletteRounds spinByRouletteRounds;
    private RouletteColor currentColor;
    private int counter;

    public SmartSpinByRouletteRounds(Roulette roulette, DoubleSupplier rouletteRounds){
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
        if(currentColor != roulette.getCurrentColor()){
            counter++;
            currentColor = roulette.getCurrentColor();
        }
        if(spinByRouletteRounds.isFinished()){
            double lastRouletteRounds = rouletteRounds.getAsDouble();
            rouletteRounds = () -> lastRouletteRounds - roulette.colorCountToRouletteRounds(counter);
            spinByRouletteRounds.initialize();
            counter = 0;
        }
    }

    @Override
    public boolean isFinished() {
        return Math.abs(rouletteRounds.getAsDouble() - roulette.colorCountToRouletteRounds(counter)) < ROULETTE_ROUNDS_TOLERANCE;
    }

    @Override
    public void end(boolean interrupted) {
        spinByRouletteRounds.end(interrupted);
    }
}

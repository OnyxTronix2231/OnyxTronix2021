package frc.robot.Roulette.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Roulette.Roulette;

import java.util.function.DoubleSupplier;

public class RouletteRoundsCompleteConfirmation extends SequentialCommandGroup {

    public RouletteRoundsCompleteConfirmation(Roulette roulette, DoubleSupplier rouletteRounds){
        super(new ParallelCommandGroup (new SpinByRouletteRounds(roulette, rouletteRounds)));
    }
}

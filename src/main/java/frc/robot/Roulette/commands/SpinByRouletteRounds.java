package frc.robot.Roulette.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Roulette.Roulette;

import java.util.function.DoubleSupplier;

public class SpinByRouletteRounds extends CommandBase {
    private Roulette roulette;
    private DoubleSupplier rouletteRounds;

    public SpinByRouletteRounds(Roulette roulette, DoubleSupplier rouletteRounds) {
        this.roulette = roulette;
        this.rouletteRounds = rouletteRounds;
        super.addRequirements(roulette);
    }

    @Override
    public void initialize() {
        roulette.reset();
        roulette.initMoveByRotation(rouletteRounds.getAsDouble());
    }

    @Override
    public void execute() {
        roulette.updateMoveByRotations(rouletteRounds.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) { // function that counts the right
        // amount of colors to ensure the right amount of rotations were complete, when said amount of
        // rotations is complete, the boolean interrupted should signal for the function end to work
        roulette.stop();
    }
}

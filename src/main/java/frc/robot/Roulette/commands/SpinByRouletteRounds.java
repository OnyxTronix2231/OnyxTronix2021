package frc.robot.Roulette.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Roulette.Roulette;

import java.util.function.DoubleSupplier;

public class SpinByRouletteRounds extends CommandBase {

    protected final Roulette roulette;
    private final DoubleSupplier rouletteRounds;

    public SpinByRouletteRounds(Roulette roulette, DoubleSupplier rouletteRounds) {
        this.roulette = roulette;
        this.rouletteRounds = rouletteRounds;
        super.addRequirements(roulette);
    }

    @Override
    public void initialize() {
        roulette.initMoveByRotation(rouletteRounds.getAsDouble());
    }

    @Override
    public void execute() {
        roulette.updateMoveByRotations(rouletteRounds.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return roulette.isOnTarget();
    }

    @Override
    public void end(boolean interrupted) {
        roulette.stop();
    }
}

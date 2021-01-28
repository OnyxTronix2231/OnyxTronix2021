package frc.robot.Roulette.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Roulette.Roulette;

import java.util.function.DoubleSupplier;

public class SpinBySpeed extends CommandBase {

    private final Roulette roulette;
    private final DoubleSupplier speed;

    public SpinBySpeed(Roulette roulette, DoubleSupplier speed) {
        this.roulette = roulette;
        this.speed = speed;
        super.addRequirements(roulette);
    }

    @Override
    public void execute() {
        roulette.setSpeed(speed.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        roulette.stop();
    }
}

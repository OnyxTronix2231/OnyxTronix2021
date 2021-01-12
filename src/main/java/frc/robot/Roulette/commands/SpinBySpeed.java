package frc.robot.Roulette.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Roulette.Roulette;

import java.util.function.Supplier;

public class SpinBySpeed extends CommandBase {
    private Roulette roulette;
    private Supplier<Double> speed;


    public SpinBySpeed(Roulette roulette, Supplier<Double> speed) {
        this.roulette = roulette;
        this.speed = speed;
        super.addRequirements(roulette);
    }


    @Override
    public void execute() {
        roulette.setSpeed(speed.get());
    }

    @Override
    public void initialize() {
        ?
    }

    @Override
    public void end(boolean interrupted) {
        roulette.stop();
    }
}

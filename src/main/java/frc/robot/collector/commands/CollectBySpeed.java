package frc.robot.collector.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.collector.Collector;

import java.util.function.DoubleSupplier;

public class CollectBySpeed extends CommandBase {

    private final Collector collector;
    private final DoubleSupplier speedSupplier;

    public CollectBySpeed(Collector collector, DoubleSupplier speedSupplier) {
        this.collector = collector;
        this.speedSupplier = speedSupplier;
        addRequirements(collector);
    }

    @Override
    public void execute() {
        collector.moveBySpeed(speedSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        collector.stop();
    }
}

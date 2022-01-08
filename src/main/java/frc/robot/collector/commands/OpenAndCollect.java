package frc.robot.collector.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.collector.Collector;

import java.util.function.DoubleSupplier;

public class OpenAndCollect extends ParallelCommandGroup {

    public OpenAndCollect(Collector collector, DoubleSupplier speedSupplier) {
        super(new OpenCollectorPistons(collector),
                new CollectBySpeed(collector, speedSupplier));
    }
}

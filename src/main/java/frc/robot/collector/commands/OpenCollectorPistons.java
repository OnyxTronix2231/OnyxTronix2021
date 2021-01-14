package frc.robot.collector.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.collector.Collector;

public class OpenCollectorPistons extends InstantCommand {
    public OpenCollectorPistons(Collector collector) {
        super(collector::openPistons);
    }
}

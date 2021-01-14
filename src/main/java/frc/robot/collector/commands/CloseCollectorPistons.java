package frc.robot.collector.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.collector.Collector;

public class CloseCollectorPistons extends InstantCommand {
    public CloseCollectorPistons(Collector collector) {
        super(collector::closePistons);
    }
}

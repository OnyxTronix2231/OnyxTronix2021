package frc.robot.collector.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.collector.Collector;

public class ClosePistons extends InstantCommand {

    public ClosePistons(Collector collector) {
        super(collector::closePistons);
    }
}

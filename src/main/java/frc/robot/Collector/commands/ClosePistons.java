package frc.robot.Collector.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Collector.Collector;

public class ClosePistons extends InstantCommand {

    public ClosePistons(Collector collector) {
        super(collector::closePistons);
    }
}

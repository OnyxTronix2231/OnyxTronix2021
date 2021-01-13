package frc.robot.collector.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.collector.Collector;

public class OpenPistons extends InstantCommand {

    public OpenPistons(Collector collector) {
        super(collector::openPistons);
    }
}

package frc.robot.Collector.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Collector.Collector;

public class OpenPistons extends InstantCommand {

    public OpenPistons(Collector collector) {
        super(collector::openPistons);
    }
}

package frc.robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.ballCollector.Collector;

public class OpenPistons extends InstantCommand {

    public OpenPistons(Collector collector) {
        super(collector::openPistons);
    }
}

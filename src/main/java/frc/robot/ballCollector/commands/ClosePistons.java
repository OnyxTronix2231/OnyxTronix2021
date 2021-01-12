package frc.robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.ballCollector.Collector;

public class ClosePistons extends InstantCommand {

    public ClosePistons(Collector collector) {
        super(collector::closePistons);
    }
}

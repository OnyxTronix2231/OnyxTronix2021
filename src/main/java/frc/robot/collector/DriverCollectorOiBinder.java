package frc.robot.collector;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.collector.commands.CloseCollectorPistons;

public class DriverCollectorOiBinder {

    public DriverCollectorOiBinder(Collector collector, Trigger closeCollector){
        closeCollector.whenActive(new CloseCollectorPistons(collector));
    }
}

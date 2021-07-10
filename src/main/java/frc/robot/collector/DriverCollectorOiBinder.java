package frc.robot.collector;

import frc.robot.collector.commands.CloseCollectorPistons;
import onyxTronix.JoystickAxis;

public class DriverCollectorOiBinder {

    public DriverCollectorOiBinder(Collector collector, JoystickAxis closeCollector) {
        closeCollector.whenActive(new CloseCollectorPistons(collector));
    }
}

package frc.robot.collector;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.collector.CollectorConstants.CLOSE_PISTON;
import static frc.robot.collector.CollectorConstants.OPEN_PISTON;

public class Collector extends SubsystemBase {

    private final CollectorComponents components;

    public Collector(CollectorComponents components) {
        this.components = components;
    }

    public void moveBySpeed(double speed) {
        components.getMasterMotor().set(speed);
    }

    public void stop() {
        moveBySpeed(0);
    }

    public void openPistons() {
        components.getSolenoid().set(OPEN_PISTON);
    }

    public void closePistons() {
        components.getSolenoid().set(CLOSE_PISTON);
    }
}

package frc.robot.collector;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.collector.CollectorConstants.CLOSE_PISTONS;
import static frc.robot.collector.CollectorConstants.OPEN_PISTONS;

public class Collector extends SubsystemBase {

    private final CollectorComponents components;

    public Collector(CollectorComponents components) {
        this.components = components;
    }

    public void moveBySpeed(double speed) {
        components.getMotor().set(speed);
    }

    public void stop() {
        moveBySpeed(0);
    }

    public void openPistons() {
        components.getSolenoid().set(OPEN_PISTONS);
    }

    public void closePistons() {
        components.getSolenoid().set(CLOSE_PISTONS);
    }
}

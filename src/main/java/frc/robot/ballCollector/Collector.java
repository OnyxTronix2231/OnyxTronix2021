package frc.robot.ballCollector;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.ballCollector.CollectorConstants.*;

public class Collector extends SubsystemBase {

    private final CollectorComponents components;

    public Collector(CollectorComponents components) {
        this.components = components;
    }

    public void collectBySpeed(double speed) {
        components.getMasterMotor().set(speed);
    }

    public void stop() {
        collectBySpeed(0);
    }

    public void openPistons() {
        components.getSolenoid().set(OPEN_PISTON);
    }

    public void closePistons() {
        components.getSolenoid().set(!OPEN_PISTON);
    }
}

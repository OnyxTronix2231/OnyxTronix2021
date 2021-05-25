package frc.robot.collector;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

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
        components.getSolenoid().set(DoubleSolenoid.Value.kForward);
    }

    public void closePistons() {
        components.getSolenoid().set(DoubleSolenoid.Value.kReverse);
    }
}

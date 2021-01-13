package frc.robot.Trigger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Trigger.TriggerConstants.OPEN_PISTON;

public class Trigger extends SubsystemBase {

    private final TriggerComponents components;

    public Trigger(TriggerComponents components) {
        this.components = components;
    }

    public void moveBallTriggerBySpeed(double speed) {
        components.getMasterMotor().set(speed);
    }

    public void stopMotor() {
        moveBallTriggerBySpeed(0);
    }

    public void openPistons() {
        components.getSolenoid().set(OPEN_PISTON);
    }

    public void closePistons() {
        components.getSolenoid().set(!OPEN_PISTON);
    }
}

package frc.robot.ballTrigger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.ballTrigger.TriggerConstants.OPEN_PISTON;

public class BallTrigger extends SubsystemBase {

    private final TriggerComponents components;

    public BallTrigger(TriggerComponents components) {
        this.components = components;
    }

    public void moveBallTriggerBySpeed(double speed) {
        components.getMasterMotor().set(speed);
    }

    public void stop() {
        moveBallTriggerBySpeed(0);
    }

    public void openPistons() {
        components.getSolenoid().set(OPEN_PISTON);
    }

    public void closePistons() {
        components.getSolenoid().set(!OPEN_PISTON);
    }
}

package frc.robot.ballCollector;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.ballCollector.BallCollectorConstants.CLOSE_SOLENOID;
import static frc.robot.ballCollector.BallCollectorConstants.OPEN_SOLENOID;

public class BallCollector extends SubsystemBase {

    private final BallCollectorComponents components;

    public BallCollector(BallCollectorComponents components) {
        this.components = components;
    }

    public void collectBySpeed(final double speed) {
        components.getMasterMotor().set(speed);
    }

    public void stopMotor() {
        collectBySpeed(0);
    }

    public void openPistons() {
        components.getSolenoid().set(OPEN_SOLENOID);
    }

    public void closePistons() {
        components.getSolenoid().set(CLOSE_SOLENOID);
    }
}

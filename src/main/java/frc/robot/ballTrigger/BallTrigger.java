package frc.robot.ballTrigger;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.ballTrigger.BallTriggerConstants.CLOSE_SOLENOID;
import static frc.robot.ballTrigger.BallTriggerConstants.OPEN_SOLENOID;

public class BallTrigger extends SubsystemBase {

    private final BallTriggerComponents components;

    public BallTrigger(BallTriggerComponents components) {
        this.components = components;
    }

    public WPI_TalonSRX getMasterMotor() {
        return components.getMasterMotor();
    }

    public Solenoid getSolenoid() {
        return components.getSolenoid();
    }

    public void moveBallTriggerBySpeed(final double speed) {
        components.getMasterMotor().set(speed);
    }

    public void stopMotor() {
        moveBallTriggerBySpeed(0);
    }

    public void openPistons() {
        components.getSolenoid().set(OPEN_SOLENOID);
    }

    public void closePistons() {
        components.getSolenoid().set(CLOSE_SOLENOID);
    }
}

package frc.robot.collector;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import static frc.robot.collector.CollectorConstants.BallCollectorConstantsA.*;

public class CollectorComponentsA implements CollectorComponents {

    private final WPI_TalonSRX motor;
    private final DoubleSolenoid solenoid;

    public CollectorComponentsA() {
        motor = new WPI_TalonSRX(MASTER_MOTOR_ID);
        motor.configFactoryDefault();
        motor.configAllSettings(getConfiguration());
        motor.setNeutralMode(NeutralMode.Brake);
        motor.enableCurrentLimit(CURRENT_LIMIT_ENABLED);
        motor.setInverted(false);

        solenoid = new DoubleSolenoid(FORWARD_CHANNEL, REVERSE_CHANNEL);
    }

    @Override
    public WPI_TalonSRX getMotor() {
        return motor;
    }

    @Override
    public DoubleSolenoid getSolenoid() {
        return solenoid;
    }

    private TalonSRXConfiguration getConfiguration() {
        final TalonSRXConfiguration config = new TalonSRXConfiguration();
        config.peakCurrentLimit = PEAK_AMP;
        config.peakCurrentDuration = PEAK_AMP_DURATION;
        config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;
        config.openloopRamp = OPEN_LOOP_RAMP;
        config.closedloopRamp = CLOSED_LOOP_RAMP;
        return config;
    }
}

package frc.robot.collector;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;

import static frc.robot.collector.CollectorConstants.BallCollectorConstantsA.*;

public class BasicCollectorComponentsA implements CollectorComponents {

    private final WPI_TalonSRX Motor;
    private final Solenoid solenoid;

    public BasicCollectorComponentsA() {
        Motor = new WPI_TalonSRX(MASTER_MOTOR_ID);
        Motor.configFactoryDefault();
        Motor.configAllSettings(getConfiguration());
        Motor.setNeutralMode(NeutralMode.Brake);
        Motor.enableCurrentLimit(CURRENT_LIMIT_ENABLED);

        solenoid = new Solenoid(SOLENOID_CHANNEL);
    }

    @Override
    public WPI_TalonSRX getMotor() {
        return Motor;
    }

    @Override
    public Solenoid getSolenoid() {
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

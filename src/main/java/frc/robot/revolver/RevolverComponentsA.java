package frc.robot.revolver;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import pid.CtrePIDController;
import pid.PIDControlMode;
import pid.interfaces.PIDController;
import sensors.counter.Counter;
import sensors.counter.CtreEncoder;

import static frc.robot.revolver.RevolverConstants.RevolverComponentsA.*;

public class RevolverComponentsA implements RevolverComponents {

    private final WPI_TalonFX motor;
    private final CtrePIDController pidController;
    private final CtreEncoder encoder;

    public RevolverComponentsA() {
        motor = new WPI_TalonFX(MASTER_MOTOR_ID);
        motor.configFactoryDefault();
        motor.configAllSettings(getConfiguration());
        motor.setNeutralMode(NeutralMode.Brake);

        encoder = new CtreEncoder(motor);

        pidController = new CtrePIDController(motor, encoder, VELOCITY_P, VELOCITY_I, VELOCITY_D, VELOCITY_F,
                PIDControlMode.Velocity);
    }

    @Override
    public WPI_TalonFX getMotor() {
        return motor;
    }

    @Override
    public Counter getEncoder() {
        return encoder;
    }

    @Override
    public PIDController getPIDController() {
        return pidController;
    }

    private TalonFXConfiguration getConfiguration() {
        final TalonFXConfiguration config = new TalonFXConfiguration();
        config.peakOutputForward = PEAK_OUTPUT_FORWARD;
        config.peakOutputReverse = PEAK_OUTPUT_REVERSE;
        config.supplyCurrLimit.currentLimit = SUPPLY_CURRENT_LIMIT;
        config.supplyCurrLimit.triggerThresholdCurrent = SUPPLY_TRIGGER_THRESHOLD_CURRENT;
        config.supplyCurrLimit.triggerThresholdTime = SUPPLY_TRIGGER_THRESHOLD_TIME;
        config.supplyCurrLimit.enable = SUPPLY_CURRENT_LIMIT_ENABLED;
        config.statorCurrLimit.currentLimit = STATOR_CURRENT_LIMIT;
        config.statorCurrLimit.triggerThresholdCurrent = STATOR_TRIGGER_THRESHOLD_CURRENT;
        config.statorCurrLimit.triggerThresholdTime = STATOR_TRIGGER_THRESHOLD_TIME;
        config.statorCurrLimit.enable = STATOR_CURRENT_LIMIT_ENABLED;
        config.closedloopRamp = CLOSED_LOOP_RAMP;
        config.openloopRamp = OPEN_LOOP_RAMP;
        return config;
    }
}

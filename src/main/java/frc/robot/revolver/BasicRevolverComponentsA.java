package frc.robot.revolver;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import pid.CtrePIDController;
import pid.PIDControlMode;
import sensors.counter.CtreEncoder;

import static frc.robot.revolver.RevolverConstants.RevolverComponentsA.*;

public class BasicRevolverComponentsA implements RevolverComponents {

    private final WPI_TalonFX masterMotor;
    private final CtrePIDController pidController;
    private final CtreEncoder encoder;

    public BasicRevolverComponentsA() {
        masterMotor = new WPI_TalonFX(MASTER_MOTOR_ID);
        masterMotor.configFactoryDefault();
        masterMotor.configAllSettings(getConfiguration());
        masterMotor.setNeutralMode(NeutralMode.Brake);

        encoder = new CtreEncoder(masterMotor, PID_SLOT);

        pidController = new CtrePIDController(masterMotor, encoder, VELOCITY_P, VELOCITY_I, VELOCITY_D, VELOCITY_F,
                PIDControlMode.Velocity);
    }

    public TalonFXConfiguration getConfiguration() {
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

    @Override
    public WPI_TalonFX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public CtreEncoder getEncoder() {
        return encoder;
    }

    @Override
    public CtrePIDController getPIDController() {
        return pidController;
    }
}

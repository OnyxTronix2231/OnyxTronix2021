package frc.robot.shooter;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;
import pid.CtrePIDController;
import pid.PIDControlMode;
import pid.PIDFTerms;
import pid.interfaces.PIDController;
import sensors.counter.Counter;
import sensors.counter.CtreEncoder;

import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.*;

public class BasicShooterComponentsA implements ShooterComponents {

    private final WPI_TalonFX masterMotor;
    private final WPI_TalonFX slaveMotor;
    private final CtreEncoder encoder;
    private final CtrePIDController controller;

    public BasicShooterComponentsA() {
        masterMotor = new WPI_TalonFX(MASTER_MOTOR_ID);
        masterMotor.configFactoryDefault();
        masterMotor.configAllSettings(getFalconConfiguration());
        masterMotor.setNeutralMode(NeutralMode.Coast);

        slaveMotor = new WPI_TalonFX(SLAVE_MOTOR_ID);
        slaveMotor.configFactoryDefault();
        slaveMotor.configAllSettings(getFalconConfiguration());
        slaveMotor.setNeutralMode(NeutralMode.Coast);
        slaveMotor.follow(masterMotor);

        encoder = new CtreEncoder(masterMotor);

        controller = new CtrePIDController(masterMotor, encoder,
                new PIDFTerms(VELOCITY_P, VELOCITY_I, VELOCITY_D, VELOCITY_F), PIDControlMode.Velocity);
    }

    private TalonFXConfiguration getFalconConfiguration() {
        final TalonFXConfiguration config = new TalonFXConfiguration();
        config.supplyCurrLimit.currentLimit = SUPPLY_CURRENT_LIMIT;
        config.supplyCurrLimit.triggerThresholdCurrent = SUPPLY_TRIGGER_THRESHOLD_CURRENT;
        config.supplyCurrLimit.triggerThresholdTime = SUPPLY_TRIGGER_THRESHOLD_TIME;
        config.supplyCurrLimit.enable = SUPPLY_CURRENT_LIMIT_ENABLED;
        config.statorCurrLimit.currentLimit = STATOR_CURRENT_LIMIT;
        config.statorCurrLimit.triggerThresholdCurrent = STATOR_TRIGGER_THRESHOLD_CURRENT;
        config.statorCurrLimit.triggerThresholdTime = STATOR_TRIGGER_THRESHOLD_TIME;
        config.statorCurrLimit.enable = STATOR_CURRENT_LIMIT_ENABLED;
        config.openloopRamp = OPEN_LOOP_RAMP;
        config.closedloopRamp = CLOSE_LOOP_RAMP;
        return config;
    }

    @Override
    public WPI_TalonFX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public IMotorController getSlaveMotor() {
        return slaveMotor;
    }

    @Override
    public Counter getEncoder() {
        return encoder;
    }

    @Override
    public PIDController getController() {
        return controller;
    }
}

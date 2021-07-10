package frc.robot.climber;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import pid.CtreController;
import pid.CtreMotionMagicController;
import pid.PIDFTerms;
import sensors.counter.Counter;
import sensors.counter.CtreEncoder;

import static frc.robot.climber.ClimberConstants.ClimberConstantsA.*;

public class BasicClimberComponentsA implements ClimberComponents {

    private final WPI_TalonFX masterMotor;
    private final WPI_TalonFX slaveMotor;
    private final CtreMotionMagicController controller;
    private final CtreEncoder encoder;

    public BasicClimberComponentsA() {

        masterMotor = new WPI_TalonFX(MASTER_MOTOR_ID);
        masterMotor.configFactoryDefault();
        masterMotor.configAllSettings(getFalconConfiguration());
        masterMotor.setNeutralMode(NeutralMode.Brake);

        slaveMotor = new WPI_TalonFX(SLAVE_MOTOR_ID);
        slaveMotor.configFactoryDefault();
        slaveMotor.configAllSettings(getFalconConfiguration());
        slaveMotor.setNeutralMode(NeutralMode.Brake);
        slaveMotor.follow(masterMotor);

        encoder = new CtreEncoder(masterMotor);

        controller = new CtreMotionMagicController(masterMotor, encoder, new PIDFTerms(KP, KI, KD, KF),
                ACCELERATION, CRUISE_VELOCITY, ACCELERATION_SMOOTHING);
    }

    @Override
    public WPI_TalonFX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public WPI_TalonFX getSlaveMotor() {
        return slaveMotor;
    }

    @Override
    public CtreController getController() {
        return controller;
    }

    @Override
    public Counter getEncoder() {
        return encoder;
    }

    private TalonFXConfiguration getFalconConfiguration() {
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

package frc.robot.turret;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import pid.CtreMotionMagicController;
import pid.interfaces.MotionMagicController;
import sensors.counter.Counter;
import sensors.counter.CtreEncoder;

import static frc.robot.turret.TurretConstants.ENCODER_OFFSET;
import static frc.robot.turret.TurretConstants.MASTER_MOTOR_ID;
import static frc.robot.turret.TurretConstants.TurretConstantsA.*;

public class TurretComponentsA implements TurretComponents {

    private final WPI_TalonSRX motor;
    private final CtreEncoder encoder;
    private final CtreMotionMagicController controller;

    public TurretComponentsA() {
        motor = new WPI_TalonSRX(MASTER_MOTOR_ID);
        motor.configFactoryDefault();
        motor.configAllSettings(getTalonSRXConfiguration());
        motor.setNeutralMode(NeutralMode.Coast);
        motor.enableCurrentLimit(CURRENT_LIMIT_ENABLED);
        motor.configSelectedFeedbackSensor(FeedbackDevice.Analog);
        motor.setSelectedSensorPosition( motor.getSensorCollection().getAnalogInRaw() - ENCODER_OFFSET, 0, 0);
        motor.setInverted(true);
        motor.setSensorPhase(true);
        encoder = new CtreEncoder(motor);
        motor.config_IntegralZone(SLOT_IDX, INTEGRAL_ZONE_BOUND);
        motor.setNeutralMode(NeutralMode.Brake);
        controller = new CtreMotionMagicController(motor, encoder,
                KP, KI, KD, KF, MAX_ACCELERATION, CRUISE_VELOCITY, ACCELERATION_SMOOTHING);
    }

    @Override
    public WPI_TalonSRX getMotor() {
        return motor;
    }

    @Override
    public Counter getEncoder() {
        return encoder;
    }

    @Override
    public MotionMagicController getTurretController() {
        return controller;
    }

    private TalonSRXConfiguration getTalonSRXConfiguration() {
        final TalonSRXConfiguration config = new TalonSRXConfiguration();
        config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;
        config.peakCurrentDuration = PEAK_AMP_DURATION;
        config.peakCurrentLimit = PEAK_AMP;
        config.openloopRamp = OPEN_LOOP_RAMP;
        config.closedloopRamp = CLOSE_LOOP_RAMP;
        return config;
    }
}

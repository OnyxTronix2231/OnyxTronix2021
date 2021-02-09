package frc.robot.ballTrigger;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import pid.CtrePIDController;
import pid.PIDControlMode;
import pid.interfaces.PIDController;
import sensors.counter.Counter;
import sensors.counter.CtreEncoder;

import static frc.robot.ballTrigger.BallTriggerConstants.BallTriggerConstantsA.*;

public class BallTriggerComponentsA implements BallTriggerComponents {

    private final WPI_TalonSRX motor;
    private final Solenoid solenoid;
    private final CtreEncoder encoder;
    private final CtrePIDController pidController;

    public BallTriggerComponentsA() {
        motor = new WPI_TalonSRX(MASTER_MOTOR_ID);
        motor.configFactoryDefault();
        motor.configAllSettings(getConfiguration());
        motor.setNeutralMode(NeutralMode.Brake);
        motor.enableCurrentLimit(CURRENT_LIMIT_ENABLED);

        solenoid = new Solenoid(SOLENOID_CHANNEL);

        encoder = new CtreEncoder(motor);

        pidController = new CtrePIDController(motor, encoder, VELOCITY_P, VELOCITY_I, VELOCITY_D, VELOCITY_F,
                PIDControlMode.Velocity);
    }

    @Override
    public WPI_TalonSRX getMotor() {
        return motor;
    }

    @Override
    public Solenoid getSolenoid() {
        return solenoid;
    }

    @Override
    public Counter getEncoder() {
        return encoder;
    }

    @Override
    public PIDController getPIDController() {
        return pidController;
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

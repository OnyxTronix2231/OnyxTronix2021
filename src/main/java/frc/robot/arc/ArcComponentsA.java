package frc.robot.arc;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import pid.CtreMotionMagicController;
import pid.PIDFTerms;
import pid.interfaces.MotionMagicController;
import sensors.Switch.TalonSrxForwardMicroswitch;
import sensors.Switch.TalonSrxReverseMicroswitch;
import sensors.counter.Counter;
import sensors.counter.CtreEncoder;

import static frc.robot.arc.ArcConstants.ArcConstantsA.*;

public class ArcComponentsA implements ArcComponents {

    private final WPI_TalonSRX motor;
    private final CtreEncoder encoder;
    private final CtreMotionMagicController controller;
    private final TalonSrxForwardMicroswitch forwardLimitSwitch;
    private final TalonSrxReverseMicroswitch reverseLimitSwitch;

    public ArcComponentsA() {
        motor = new WPI_TalonSRX(MOTOR_ID);
        motor.configFactoryDefault();
        motor.configAllSettings(getTalonSRXConfiguration());
        motor.setNeutralMode(NeutralMode.Brake);
        motor.enableCurrentLimit(CURRENT_LIMIT_ENABLED);

        encoder = new CtreEncoder(motor);

        controller = new CtreMotionMagicController(motor, encoder,
                new PIDFTerms(KP, KI, KD, KF),
                MAX_ACCELERATION, CRUISE_VELOCITY, ACCELERATION_SMOOTHING);

        forwardLimitSwitch = new TalonSrxForwardMicroswitch(motor, LimitSwitchSource.FeedbackConnector,
                LimitSwitchNormal.NormallyOpen);
        reverseLimitSwitch = new TalonSrxReverseMicroswitch(motor, LimitSwitchSource.FeedbackConnector,
                LimitSwitchNormal.NormallyOpen);
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
    public MotionMagicController getController() {
        return controller;
    }

    @Override
    public TalonSrxReverseMicroswitch getReverseLimitSwitch() {
        return reverseLimitSwitch;
    }

    @Override
    public TalonSrxForwardMicroswitch getForwardLimitSwitch() {
        return forwardLimitSwitch;
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

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

public class BasicArcComponentsA implements ArcComponents {

    private final WPI_TalonSRX masterMotor;
    private final CtreEncoder encoder;
    private final CtreMotionMagicController controller;
    private final TalonSrxForwardMicroswitch forwardLimitswitch;
    private final TalonSrxReverseMicroswitch reverseLimitswitch;

    public BasicArcComponentsA() {
        masterMotor = new WPI_TalonSRX(MASTER_MOTOR_ID);
        masterMotor.configFactoryDefault();
        masterMotor.configAllSettings(getTalonSRXConfiguration());
        masterMotor.setNeutralMode(NeutralMode.Brake);
        masterMotor.enableCurrentLimit(CURRENT_LIMIT_ENABLED);

        encoder = new CtreEncoder(masterMotor);

        controller = new CtreMotionMagicController(masterMotor,
                new CtreEncoder(masterMotor),
                new PIDFTerms(VELOCITY_P, VELOCITY_I, VELOCITY_D, VELOCITY_F),
                MAX_ACCELERATION, CRUISE_VELOCITY, ACCELERATION_SMOOTHING);

        forwardLimitswitch = new TalonSrxForwardMicroswitch(masterMotor, LimitSwitchSource.FeedbackConnector,
                LimitSwitchNormal.NormallyOpen);
        reverseLimitswitch = new TalonSrxReverseMicroswitch(masterMotor, LimitSwitchSource.FeedbackConnector,
                LimitSwitchNormal.NormallyOpen);
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

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
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
        return reverseLimitswitch;
    }

    @Override
    public TalonSrxForwardMicroswitch getForwardLimitSwitch() {
        return forwardLimitswitch;
    }
}

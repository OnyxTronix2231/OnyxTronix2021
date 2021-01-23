package frc.robot.arc;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import pid.CtreMotionMagicController;
import pid.PIDFTerms;
import pid.interfaces.MotionMagicController;
import sensors.counter.Counter;
import sensors.counter.CtreEncoder;

import static frc.robot.arc.ArcConstants.ArcConstantsA.*;

public class BasicArcComponentsA implements ArcComponents {

    private final WPI_TalonSRX masterMotor;
    private final CtreEncoder encoder;
    private final CtreMotionMagicController controller;

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
}

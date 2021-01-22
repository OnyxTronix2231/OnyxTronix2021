package frc.robot.arc;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import pid.CtreMotionMagicController;
import pid.PIDFTerms;
import pid.interfaces.MotionMagicController;
import sensors.counter.Counter;
import sensors.counter.CtreEncoder;

import static frc.robot.arc.arcConstants.ArcConstantsA.*;

public class BasicArcComponentsA implements ArcComponents {

    private final WPI_TalonSRX masterMotor;
    private final CtreEncoder arcMotorEncoder;
    private final CtreMotionMagicController arcController;

    public BasicArcComponentsA() {
        masterMotor = new WPI_TalonSRX(MASTER_MOTOR_ID);
        masterMotor.configFactoryDefault();
        masterMotor.configAllSettings(getTalonSRXConfiguration());
        masterMotor.setNeutralMode(NeutralMode.Brake);
        masterMotor.enableCurrentLimit(ENABLE_CURRENT_LIMIT);

        arcMotorEncoder = new CtreEncoder(masterMotor);

        arcController = new CtreMotionMagicController(masterMotor,
                new CtreEncoder(masterMotor),
                new PIDFTerms(ARC_VELOCITY_P, ARC_VELOCITY_I, ARC_VELOCITY_D, ARC_VELOCITY_F),
                ARC_MAX_ACCELERATION, ARC_CRUISE_VELOCITY, ARC_ACCELERATION_SMOOTHING);
    }

    private TalonSRXConfiguration getTalonSRXConfiguration() {
        final TalonSRXConfiguration config = new TalonSRXConfiguration();
        config.continuousCurrentLimit = ARC_CONTINUOUS_CURRENT_LIMIT;
        config.peakCurrentDuration = ARC_PEAK_AMP_DURATION;
        config.peakCurrentLimit = ARC_PEAK_AMP;
        config.openloopRamp = ARC_OPEN_LOOP_RAMP;
        config.closedloopRamp = ARC_CLOSE_LOOP_RAMP;
        return config;
    }

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public Counter getArcMotorEncoder() {
        return arcMotorEncoder;
    }

    @Override
    public MotionMagicController getArcController() {
        return arcController;
    }
}

package frc.robot.ballTrigger;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.system.plant.DCMotor;
import pid.CtrePIDController;
import pid.PIDControlMode;
import sensors.counter.CtreEncoder;

import static frc.robot.ballTrigger.BallTriggerConstants.BallTriggerConstantsA.*;

public class BasicBallTriggerComponentsA implements BallTriggerComponents {

    private final WPI_TalonSRX masterMotor;
    private final Solenoid solenoid;
    private final CtrePIDController pidController;
    private final CtreEncoder encoder;
    private final FlywheelSim flywheelSim;

    public BasicBallTriggerComponentsA() {
        masterMotor = new WPI_TalonSRX(MASTER_MOTOR_ID);
        masterMotor.configFactoryDefault();
        masterMotor.configAllSettings(getConfiguration());
        masterMotor.setNeutralMode(NeutralMode.Brake);
        masterMotor.enableCurrentLimit(CURRENT_LIMIT_ENABLED);

        solenoid = new Solenoid(SOLENOID_CHANNEL);

        encoder = new CtreEncoder(masterMotor);

        pidController = new CtrePIDController(masterMotor, encoder, VELOCITY_P, VELOCITY_I, VELOCITY_D, VELOCITY_F,
                PIDControlMode.Velocity);

        flywheelSim = new FlywheelSim(DCMotor.getVex775Pro(1), 1, 0.01);
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

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public Solenoid getSolenoid() {
        return solenoid;
    }

    @Override
    public CtreEncoder getEncoder() {
        return encoder;
    }

    @Override
    public CtrePIDController getPIDController() {
        return pidController;
    }

    @Override
    public FlywheelSim getFlyWheelSim() {
        return flywheelSim;
    }
}

package frc.robot.ballTrigger;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Solenoid;
import pid.CtrePIDController;
import pid.PIDControlMode;
import pid.interfaces.PIDController;
import sensors.counter.Counter;
import sensors.counter.CtreEncoder;

import static frc.robot.ballTrigger.BallTriggerConstants.BallTriggerConstantsA.*;

public class BallTriggerComponentsA implements BallTriggerComponents {

    private final WPI_TalonSRX masterMotor;
    private final WPI_TalonSRX slaveMotor;
    private final Solenoid solenoid;
    private final CtreEncoder encoder;
    private final CtrePIDController pidController;

    public BallTriggerComponentsA() {
        masterMotor = new WPI_TalonSRX(MASTER_MOTOR_ID);
        masterMotor.configFactoryDefault();
        masterMotor.configAllSettings(getConfiguration());
        masterMotor.setNeutralMode(NeutralMode.Brake);
        masterMotor.enableCurrentLimit(CURRENT_LIMIT_ENABLED);

        slaveMotor = new WPI_TalonSRX(SLAVE_MOTOR_ID);
        slaveMotor.configFactoryDefault();
        slaveMotor.configAllSettings(getConfiguration());
        slaveMotor.setNeutralMode(NeutralMode.Brake);
        slaveMotor.enableCurrentLimit(CURRENT_LIMIT_ENABLED);
        slaveMotor.follow(masterMotor);

        solenoid = new Solenoid(SOLENOID_CHANNEL);

        encoder = new CtreEncoder(masterMotor);

        pidController = new CtrePIDController(masterMotor, encoder, VELOCITY_P, VELOCITY_I, VELOCITY_D, VELOCITY_F,
                PIDControlMode.Velocity);
    }

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public IMotorController getSlaveMotor() {
        return slaveMotor;
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

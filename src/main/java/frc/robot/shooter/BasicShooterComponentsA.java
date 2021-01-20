package frc.robot.shooter;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;
import pid.CtreMotionMagicController;
import pid.CtrePIDController;
import pid.PIDControlMode;
import pid.PIDFTerms;
import pid.interfaces.MotionMagicController;
import pid.interfaces.PIDController;
import sensors.counter.CtreEncoder;

import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.*;

public class BasicShooterComponentsA implements ShooterComponents {

    private final WPI_TalonFX masterShooterMotor;
    private final WPI_TalonFX slaveShooterMotor;
    private final WPI_TalonSRX angularMotor;
    private final CtreMotionMagicController angularController;
    private final CtrePIDController ShooterController;

    public BasicShooterComponentsA() {
        masterShooterMotor = new WPI_TalonFX(MASTER_SHOOTER_MOTOR_ID);
        masterShooterMotor.configFactoryDefault();
        masterShooterMotor.configAllSettings(getFalconConfiguration());
        masterShooterMotor.setNeutralMode(NeutralMode.Coast);

        slaveShooterMotor = new WPI_TalonFX(SLAVE_SHOOTER_MOTOR_ID);
        slaveShooterMotor.configFactoryDefault();
        slaveShooterMotor.configAllSettings(getFalconConfiguration());
        slaveShooterMotor.setNeutralMode(NeutralMode.Coast);
        slaveShooterMotor.follow(masterShooterMotor);

        angularMotor = new WPI_TalonSRX(ANGULAR_MOTOR_ID);
        angularMotor.configFactoryDefault();
        angularMotor.configAllSettings(getTalonSRXConfiguration());
        angularMotor.setNeutralMode(NeutralMode.Brake);
        angularMotor.enableCurrentLimit(false);

        angularController = new CtreMotionMagicController(angularMotor,
                new CtreEncoder(angularMotor),
                new PIDFTerms(ANGULAR_MOTOR_VELOCITY_P, ANGULAR_MOTOR_VELOCITY_I, ANGULAR_MOTOR_VELOCITY_D, ANGULAR_MOTOR_VELOCITY_F),
                ANGULAR_MOTOR_MAX_ACCELERATION, ANGULAR_MOTOR_CRUISE_VELOCITY, ANGULAR_MOTOR_ACCELERATION_SMOOTHING);
        ShooterController = new CtrePIDController(masterShooterMotor,
                new CtreEncoder(masterShooterMotor),
                new PIDFTerms(SHOOTER_VELOCITY_P, SHOOTER_VELOCITY_I, SHOOTER_VELOCITY_D, SHOOTER_VELOCITY_F), PIDControlMode.Velocity);
    }

    private TalonFXConfiguration getFalconConfiguration() {
        final TalonFXConfiguration config = new TalonFXConfiguration();
        config.supplyCurrLimit.currentLimit = CURRENT_LIMIT;
        config.supplyCurrLimit.triggerThresholdCurrent = TRIGGER_THRESHOLD_CURRENT;
        config.supplyCurrLimit.triggerThresholdTime = TRIGGER_THRESHOLD_TIME;
        config.supplyCurrLimit.enable = true;
        config.statorCurrLimit.currentLimit = STATOR_CURRENT_LIMIT;
        config.statorCurrLimit.triggerThresholdCurrent = STATOR_TRIGGER_THRESHOLD_CURRENT;
        config.statorCurrLimit.triggerThresholdTime = STATOR_TRIGGER_THRESHOLD_TIME;
        config.statorCurrLimit.enable = false;
        config.openloopRamp = SHOOTER_MOTOR_OPEN_LOOP_RAMP;
        config.closedloopRamp = SHOOTER_MOTOR_CLOSE_LOOP_RAMP;
        return config;
    }

    private TalonSRXConfiguration getTalonSRXConfiguration() {
        final TalonSRXConfiguration config = new TalonSRXConfiguration();
        config.continuousCurrentLimit = ANGULAR_MOTOR_CONTINUOUS_CURRENT_LIMIT;
        config.peakCurrentDuration = ANGULAR_MOTOR_PEAK_AMP_DURATION;
        config.peakCurrentLimit = ANGULAR_MOTOR_PEAK_AMP;
        config.openloopRamp = ANGULAR_MOTOR_OPEN_LOOP_RAMP;
        config.closedloopRamp = ANGULAR_MOTOR_CLOSE_LOOP_RAMP;
        return config;
    }

    @Override
    public WPI_TalonFX getMasterShooterMotor() {
        return masterShooterMotor;
    }

    @Override
    public IMotorController getSlaveShooterMotor() {
        return slaveShooterMotor;
    }

    @Override
    public WPI_TalonSRX getAngularMotor() {
        return angularMotor;
    }

    @Override
    public PIDController getShooterController() {
        return ShooterController;
    }

    @Override
    public MotionMagicController getAngularController() {
        return angularController;
    }
}

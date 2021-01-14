package frc.robot.shooter;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import pid.CtreMotionMagicController;
import pid.CtrePIDController;
import pid.PIDControlMode;
import pid.PIDFTerms;
import sensors.counter.CtreEncoder;

import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.*;

public class BasicShooterComponentsA implements ShooterComponents {

    private final WPI_TalonFX masterMotor;
    private final WPI_TalonFX slaveMotor;
    private final TalonSRX angleMotor;
    private final CtreMotionMagicController ctreMotionMagicController;
    private final  CtrePIDController ctrePIDController;

    public BasicShooterComponentsA() {
        masterMotor = new WPI_TalonFX(ShooterConstants.ShooterConstantsA.MASTER_MOTOR_ID);
        masterMotor.configFactoryDefault();
        masterMotor.configAllSettings(getFalconConfiguration());
        masterMotor.setNeutralMode(NeutralMode.Coast);
        masterMotor.setInverted(true);

        slaveMotor = new WPI_TalonFX(ShooterConstants.ShooterConstantsA.SLAVE_MOTOR_ID);
        slaveMotor.configAllSettings(getFalconConfiguration());
        slaveMotor.configFactoryDefault();
        slaveMotor.setNeutralMode(NeutralMode.Coast);
        slaveMotor.follow(masterMotor);

        angleMotor = new TalonSRX(4);
        angleMotor.configFactoryDefault();
        angleMotor.setNeutralMode(NeutralMode.Brake);
        angleMotor.configAllSettings(getTalonSRXConfiguration());

         ctreMotionMagicController = new CtreMotionMagicController(angleMotor,
                new CtreEncoder(angleMotor),
                new PIDFTerms(ANGLE_MOTOR_VELOCITY_P, ANGLE_MOTOR_VELOCITY_I, ANGLE_MOTOR_VELOCITY_D, ANGLE_VELOCITY_F),
                ANGLE_MOTOR_MAX_ACCELERATION, ANGLE_MOTOR_CRUISE_VELOCITY, ANGLE_MOTOR_ACCELERATION_SMOOTHING);
         ctrePIDController = new CtrePIDController(masterMotor,
                new CtreEncoder(masterMotor),
                new PIDFTerms(SHOOTER_VELOCITY_P, SHOOTER_VELOCITY_I, SHOOTER_VELOCITY_D, SHOOTER_VELOCITY_F), PIDControlMode.Velocity);
    }

    private TalonFXConfiguration getFalconConfiguration() {
        final TalonFXConfiguration config = new TalonFXConfiguration();
        config.supplyCurrLimit.currentLimit = CURRENT_LIMIT;
        config.supplyCurrLimit.triggerThresholdCurrent = TRIGGER_THRESHOLD_CURRENT;
        config.supplyCurrLimit.triggerThresholdTime = TRIGGER_THRESHOLD_TIME;
        config.closedloopRamp = CLOSE_LOOP_RAMP;
        config.openloopRamp = OPEN_LOOP_RAMP;
        config.supplyCurrLimit.enable = true;
        return config;
    }

    private TalonSRXConfiguration getTalonSRXConfiguration() {
        final TalonSRXConfiguration config = new TalonSRXConfiguration();
        return config;
    }

    @Override
    public WPI_TalonFX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public TalonSRX getAngleMotor() {
        return angleMotor;
    }

    @Override
    public CtreMotionMagicController getCtreMotionMagicController() {
        return ctreMotionMagicController;
    }

    @Override
    public CtrePIDController getCtrePIDController() {
        return ctrePIDController;
    }

    @Override
    public IMotorController getSlaveMotor() {
        return slaveMotor;
    }
}

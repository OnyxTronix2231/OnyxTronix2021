package frc.robot.shooter;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import static com.ctre.phoenix.motorcontrol.FeedbackDevice.IntegratedSensor;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.*;

public class BasicShooterComponentsA implements ShooterComponents {

    private final WPI_TalonFX masterMotor;
    private final WPI_TalonFX slaveMotor;
    private final TalonSRX angleMotor;

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
    }

    private TalonFXConfiguration getFalconConfiguration() {
        final TalonFXConfiguration config = new TalonFXConfiguration();
        config.slot0.kP = SHOOTER_VELOCITY_P;
        config.slot0.kI = SHOOTER_VELOCITY_I;
        config.slot0.kD = SHOOTER_VELOCITY_D;
        config.slot0.kF = SHOOTER_VELOCITY_F;
        config.primaryPID.selectedFeedbackSensor = IntegratedSensor;
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
        config.slot0.kP = ANGLE_VELOCITY_P;
        config.slot0.kI = ANGLE_VELOCITY_I;
        config.slot0.kD = ANGLE_VELOCITY_D;
        config.slot0.kF = ANGLE_VELOCITY_F;
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
    public IMotorController getSlaveMotor() {
        return slaveMotor;
    }
}

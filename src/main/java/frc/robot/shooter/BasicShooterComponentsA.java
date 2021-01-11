package frc.robot.shooter;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.Solenoid;

public class BasicShooterComponentsA implements ShooterComponents {

    private final WPI_TalonFX masterMotor;
    private final WPI_TalonFX slaveMotor;
    private final Solenoid solenoid;

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

        solenoid = new Solenoid(ShooterConstants.ShooterConstantsA.SOLENOID_PORT);
    }


    private TalonFXConfiguration getFalconConfiguration() {
        final TalonFXConfiguration config = new TalonFXConfiguration();
        config.slot0.kP = ShooterConstants.ShooterConstantsA.VELOCITY_P;
        config.slot0.kI = ShooterConstants.ShooterConstantsA.VELOCITY_I;
        config.slot0.kD = ShooterConstants.ShooterConstantsA.VELOCITY_D;
        config.slot0.kF = ShooterConstants.ShooterConstantsA.VELOCITY_F;
        config.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
        config.supplyCurrLimit.currentLimit = ShooterConstants.ShooterConstantsA.CURRENT_LIMIT;
        config.supplyCurrLimit.triggerThresholdCurrent = ShooterConstants.ShooterConstantsA.TRIGGER_THRESHOLD_CURRENT;
        config.supplyCurrLimit.triggerThresholdTime = ShooterConstants.ShooterConstantsA.TRIGGER_THRESHOLD_TIME;
        config.closedloopRamp = ShooterConstants.ShooterConstantsA.CLOSE_LOOP_RAMP;
        config.openloopRamp = ShooterConstants.ShooterConstantsA.OPEN_LOOP_RAMP;
        config.supplyCurrLimit.enable = true;
        return config;
    }

    @Override
    public WPI_TalonFX getMasterMotor() {
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
}

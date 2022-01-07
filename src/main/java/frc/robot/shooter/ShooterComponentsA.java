package frc.robot.shooter;

import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.CLOSE_LOOP_RAMP;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.MASTER_MOTOR_ID;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.OPEN_LOOP_RAMP;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.SLAVE_MOTOR_ID;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.STATOR_CURRENT_LIMIT;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.STATOR_CURRENT_LIMIT_ENABLED;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.STATOR_TRIGGER_THRESHOLD_CURRENT;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.STATOR_TRIGGER_THRESHOLD_TIME;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.SUPPLY_CURRENT_LIMIT;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.SUPPLY_CURRENT_LIMIT_ENABLED;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.SUPPLY_TRIGGER_THRESHOLD_CURRENT;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.SUPPLY_TRIGGER_THRESHOLD_TIME;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.VELOCITY_D;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.VELOCITY_F;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.VELOCITY_I;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.VELOCITY_P;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import pid.CtrePIDController;
import pid.PIDControlMode;
import pid.PIDFTerms;
import pid.interfaces.PIDController;
import sensors.counter.Counter;
import sensors.counter.CtreEncoder;

public class ShooterComponentsA implements ShooterComponents {

    private final WPI_TalonFX masterMotor;
    private final WPI_TalonFX slaveMotor;
    private final CtreEncoder encoder;
    private final CtrePIDController controller;

    public ShooterComponentsA() {
        masterMotor = new WPI_TalonFX(MASTER_MOTOR_ID);
        masterMotor.configFactoryDefault();
        masterMotor.configAllSettings(getFalconConfiguration());
        masterMotor.setNeutralMode(NeutralMode.Coast);
        masterMotor.setInverted(false);

        slaveMotor = new WPI_TalonFX(SLAVE_MOTOR_ID);
        slaveMotor.configFactoryDefault();
        slaveMotor.configAllSettings(getFalconConfiguration());
        slaveMotor.setNeutralMode(NeutralMode.Coast);
        slaveMotor.follow(masterMotor);
        slaveMotor.setInverted(true);

        encoder = new CtreEncoder(masterMotor);

        controller = new CtrePIDController(masterMotor, encoder,
                new PIDFTerms(VELOCITY_P, VELOCITY_I, VELOCITY_D, VELOCITY_F), PIDControlMode.Velocity);
        controller.setPIDFTerms(controller.getPIDFTerms());
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
    public Counter getEncoder() {
        return encoder;
    }

    @Override
    public PIDController getController() {
        return controller;
    }

    private TalonFXConfiguration getFalconConfiguration() {
        final TalonFXConfiguration config = new TalonFXConfiguration();
        config.supplyCurrLimit.currentLimit = SUPPLY_CURRENT_LIMIT;
        config.supplyCurrLimit.triggerThresholdCurrent = SUPPLY_TRIGGER_THRESHOLD_CURRENT;
        config.supplyCurrLimit.triggerThresholdTime = SUPPLY_TRIGGER_THRESHOLD_TIME;
        config.supplyCurrLimit.enable = SUPPLY_CURRENT_LIMIT_ENABLED;
        config.statorCurrLimit.currentLimit = STATOR_CURRENT_LIMIT;
        config.statorCurrLimit.triggerThresholdCurrent = STATOR_TRIGGER_THRESHOLD_CURRENT;
        config.statorCurrLimit.triggerThresholdTime = STATOR_TRIGGER_THRESHOLD_TIME;
        config.statorCurrLimit.enable = STATOR_CURRENT_LIMIT_ENABLED;
        config.openloopRamp = OPEN_LOOP_RAMP;
        config.closedloopRamp = CLOSE_LOOP_RAMP;
        return config;
    }
}

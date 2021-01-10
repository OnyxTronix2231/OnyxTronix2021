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
    masterMotor = new WPI_TalonFX(ShooterConstants.ShooterComponentsA.MASTER_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.configAllSettings(getFalconConfiguration());
    masterMotor.setNeutralMode(NeutralMode.Coast);
    masterMotor.setInverted(true);

    slaveMotor = new WPI_TalonFX(ShooterConstants.ShooterComponentsA.SLAVE_PORT);
    slaveMotor.configAllSettings(getFalconConfiguration());
    slaveMotor.configFactoryDefault();
    slaveMotor.setNeutralMode(NeutralMode.Coast);
    slaveMotor.follow(masterMotor);

    solenoid = new Solenoid(ShooterConstants.ShooterComponentsA.SOLENOID_PORT);
  }


  private TalonFXConfiguration getFalconConfiguration() {
    final TalonFXConfiguration config = new TalonFXConfiguration();
    config.slot0.kP = ShooterConstants.ShooterComponentsA.VELOCITY_P;
    config.slot0.kI = ShooterConstants.ShooterComponentsA.VELOCITY_I;
    config.slot0.kD = ShooterConstants.ShooterComponentsA.VELOCITY_D;
    config.slot0.kF = ShooterConstants.ShooterComponentsA.VELOCITY_F;
    config.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
    config.supplyCurrLimit.currentLimit = ShooterConstants.ShooterComponentsA.CURRENT_LIMIT;
    config.supplyCurrLimit.triggerThresholdCurrent = ShooterConstants.ShooterComponentsA.TRIGGER_THRESHOLD_CURRENT;
    config.supplyCurrLimit.triggerThresholdTime = ShooterConstants.ShooterComponentsA.TRIGGER_THRESHOLD_TIME;
    config.closedloopRamp = ShooterConstants.ShooterComponentsA.CLOSE_LOOP_RAMP;
    config.openloopRamp = ShooterConstants.ShooterComponentsA.OPEN_LOOP_RAMP;
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

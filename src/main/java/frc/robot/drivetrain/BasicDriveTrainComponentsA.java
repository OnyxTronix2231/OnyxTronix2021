package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainConstantsA.*;


public class BasicDriveTrainComponentsA implements DriveTrainComponents {

  private final WPI_TalonFX rightMaster;
  private final WPI_TalonFX rightSlave;
  private final WPI_TalonFX leftMaster;
  private final WPI_TalonFX leftSlave;
  private final NormalizedPigeonIMU normalizedPigeonIMU;

  public BasicDriveTrainComponentsA() {
    rightMaster = new WPI_TalonFX(RIGHT_MASTER_PORT);
    rightMaster.configFactoryDefault();
    rightMaster.configAllSettings(getFalconConfiguration());
    rightMaster.setInverted(true);
    rightMaster.setNeutralMode(NeutralMode.Brake);

    rightSlave = new WPI_TalonFX(RIGHT_SLAVE_PORT);
    rightSlave.configFactoryDefault();
    rightSlave.configAllSettings(getFalconConfiguration());
    rightSlave.setInverted(true);
    rightSlave.setNeutralMode(NeutralMode.Brake);
    rightSlave.follow(rightMaster);

    leftMaster = new WPI_TalonFX(LEFT_MASTER_PORT);
    leftMaster.configFactoryDefault();
    leftMaster.configAllSettings(getFalconConfiguration());
    leftMaster.setNeutralMode(NeutralMode.Brake);

    leftSlave = new WPI_TalonFX(LEFT_SLAVE_PORT);
    leftSlave.configFactoryDefault();
    leftSlave.configAllSettings(getFalconConfiguration());
    leftSlave.setNeutralMode(NeutralMode.Brake);
    leftSlave.follow(leftMaster);

    normalizedPigeonIMU = new NormalizedPigeonIMU(PIGEON_PORT);
    normalizedPigeonIMU.setYaw(0);
  }

  @Override
  public WPI_TalonFX getRightMasterMotor() {
    return rightMaster;
  }

  @Override
  public IMotorController getRightSlaveMotor() {
    return rightSlave;
  }

  @Override
  public WPI_TalonFX getLeftMasterMotor() {
    return leftMaster;
  }

  @Override
  public IMotorController getLeftSlaveMotor() {
    return leftSlave;
  }

  @Override
  public NormalizedPigeonIMU getPigeonIMU() {
    return normalizedPigeonIMU;
  }

  private TalonFXConfiguration getFalconConfiguration() {
    final TalonFXConfiguration config = new TalonFXConfiguration();
    config.peakOutputForward = MAX_OUTPUT_FORWARD;
    config.peakOutputReverse = MAX_OUTPUT_REVERSE;
    config.supplyCurrLimit.currentLimit = SUPPLY_CURRENT_LIMIT;
    config.supplyCurrLimit.triggerThresholdCurrent = SUPPLY_TRIGGER_THRESHOLD_CURRENT;
    config.supplyCurrLimit.triggerThresholdTime = SUPPLY_TRIGGER_THRESHOLD_TIME;
    config.supplyCurrLimit.enable = SUPPLY_CURRENT_LIMIT_ENABLED;
    config.statorCurrLimit.currentLimit = STATOR_CURRENT_LIMIT;
    config.statorCurrLimit.triggerThresholdCurrent = STATOR_TRIGGER_THRESHOLD_CURRENT;
    config.statorCurrLimit.triggerThresholdTime = STATOR_TRIGGER_THRESHOLD_TIME;
    config.statorCurrLimit.enable = STATOR_CURRENT_LIMIT_ENABLED;
    return config;
  }
}

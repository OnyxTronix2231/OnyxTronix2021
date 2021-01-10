package frc.robot.drivetrain;

import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.CURRENT_LIMIT;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.LEFT_MASTER_PORT;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.LEFT_SLAVE_PORT;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.MAX_OUTPUT_FORWARD;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.MAX_OUTPUT_REVERSE;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.RIGHT_MASTER_PORT;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.RIGHT_SLAVE_PORT;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TRIGGER_THRESHOLD_CURRENT;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TRIGGER_THRESHOLD_TIME;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;


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
    config.supplyCurrLimit.currentLimit = CURRENT_LIMIT;
    config.supplyCurrLimit.triggerThresholdCurrent = TRIGGER_THRESHOLD_CURRENT;
    config.supplyCurrLimit.triggerThresholdTime = TRIGGER_THRESHOLD_TIME;
    config.supplyCurrLimit.enable = true;
    return config;
  }
}

package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.drivetrain.utils.NormalizedPigeonIMU;

import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.*;


public class DriveTrainComponentsA implements DriveTrainComponents {

    private final WPI_TalonFX leftMaster;
    private final WPI_TalonFX leftSlave;
    private final WPI_TalonFX rightMaster;
    private final WPI_TalonFX rightSlave;
    private final NormalizedPigeonIMU pigeonIMU;

    public DriveTrainComponentsA() {
        rightMaster = new WPI_TalonFX(RIGHT_MASTER_PORT);
        rightMaster.configFactoryDefault();
        rightMaster.configAllSettings(getFalconConfiguration());
        rightMaster.setInverted(true);
        rightMaster.setNeutralMode(NeutralMode.Brake);
        rightMaster.configOpenloopRamp(0.3);

        rightSlave = new WPI_TalonFX(RIGHT_SLAVE_PORT);
        rightSlave.configFactoryDefault();
        rightSlave.configAllSettings(getFalconConfiguration());
        rightSlave.setInverted(true);
        rightSlave.setNeutralMode(NeutralMode.Brake);
        rightSlave.follow(rightMaster);
        rightSlave.configOpenloopRamp(0.3);

        leftMaster = new WPI_TalonFX(LEFT_MASTER_PORT);
        leftMaster.configFactoryDefault();
        leftMaster.configAllSettings(getFalconConfiguration());
        leftMaster.setNeutralMode(NeutralMode.Brake);
        leftMaster.configOpenloopRamp(0.3);

        leftSlave = new WPI_TalonFX(LEFT_SLAVE_PORT);
        leftSlave.configFactoryDefault();
        leftSlave.configAllSettings(getFalconConfiguration());
        leftSlave.setNeutralMode(NeutralMode.Brake);
        leftSlave.follow(leftMaster);
        leftSlave.configOpenloopRamp(0.3);


        pigeonIMU = new NormalizedPigeonIMU(PIGEON_PORT);
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
    public WPI_TalonFX getRightMasterMotor() {
        return rightMaster;
    }

    @Override
    public IMotorController getRightSlaveMotor() {
        return rightSlave;
    }

    @Override
    public NormalizedPigeonIMU getNormelizedPigeonIMU() {
        return pigeonIMU;
    }

    private TalonFXConfiguration getFalconConfiguration() {
        final TalonFXConfiguration config = new TalonFXConfiguration();
        config.peakOutputForward = MAX_OUTPUT_FORWARD;
        config.peakOutputReverse = MAX_OUTPUT_REVERSE;
        config.supplyCurrLimit.currentLimit = CURRENT_LIMIT;
        config.supplyCurrLimit.triggerThresholdCurrent = TRIGGER_THRESHOLD_CURRENT;
        config.supplyCurrLimit.triggerThresholdTime = TRIGGER_THRESHOLD_TIME;
        config.supplyCurrLimit.enable = false;
        return config;
    }
}

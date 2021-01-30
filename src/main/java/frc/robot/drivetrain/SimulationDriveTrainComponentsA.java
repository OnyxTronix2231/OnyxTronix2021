package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.simulation.AnalogGyroSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;

import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.*;

public class SimulationDriveTrainComponentsA implements SimulationDriveTrainComponents {

    private final WPI_TalonSRX rightMaster;
    private final WPI_TalonSRX rightSlave;
    private final WPI_TalonSRX leftMaster;
    private final WPI_TalonSRX leftSlave;
    private final SpeedControllerGroup rightMotors;
    private final SpeedControllerGroup leftMotors;
    private final AnalogGyroSim analogGyroSim;

    private final Field2d field2d;

    public SimulationDriveTrainComponentsA() {
        rightMaster = new WPI_TalonSRX(RIGHT_MASTER_PORT);
        rightMaster.configFactoryDefault();
        rightMaster.configAllSettings(getSRXConfiguration());
        rightMaster.setInverted(false);
        rightMaster.setNeutralMode(NeutralMode.Brake);

        rightSlave = new WPI_TalonSRX(RIGHT_SLAVE_PORT);
        rightSlave.configFactoryDefault();
        rightSlave.configAllSettings(getSRXConfiguration());
        rightSlave.setInverted(false);
        rightSlave.setNeutralMode(NeutralMode.Brake);
        rightSlave.follow(rightMaster);

        rightMotors = new SpeedControllerGroup(rightMaster, rightSlave);

        leftMaster = new WPI_TalonSRX(LEFT_MASTER_PORT);
        leftMaster.configFactoryDefault();
        leftMaster.configAllSettings(getSRXConfiguration());
        leftMaster.setNeutralMode(NeutralMode.Brake);

        leftSlave = new WPI_TalonSRX(LEFT_SLAVE_PORT);
        leftSlave.configFactoryDefault();
        leftSlave.configAllSettings(getSRXConfiguration());
        leftSlave.setNeutralMode(NeutralMode.Brake);
        leftSlave.follow(leftMaster);

        leftMotors = new SpeedControllerGroup(leftMaster, leftSlave);

        analogGyroSim = new AnalogGyroSim(0);

        field2d = new Field2d();
    }

    @Override
    public AnalogGyroSim getAnalogGyroSim() {
        return analogGyroSim;
    }

    @Override
    public WPI_TalonSRX getRightMasterMotor() {
        return rightMaster;
    }

    @Override
    public IMotorController getRightSlaveMotor() {
        return rightSlave;
    }

    @Override
    public WPI_TalonSRX getLeftMasterMotor() {
        return leftMaster;
    }

    @Override
    public IMotorController getLeftSlaveMotor() {
        return leftSlave;
    }

    @Override
    public Field2d getField2d() {
        return field2d;
    }

    public SpeedControllerGroup getRightMotors() {
        return rightMotors;
    }

    public SpeedControllerGroup getLeftMotors() {
        return leftMotors;
    }

    private TalonSRXConfiguration getSRXConfiguration() {
        final TalonSRXConfiguration config = new TalonSRXConfiguration();
        config.peakOutputForward = MAX_OUTPUT_FORWARD;
        config.peakOutputReverse = MAX_OUTPUT_REVERSE;
        config.closedloopRamp = CLOSED_LOOP_RAMP;
        config.openloopRamp = OPEN_LOOP_RAMP;
        config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;
        config.peakCurrentDuration = PEAK_CURRENT_DURATION;
        return config;
    }
}

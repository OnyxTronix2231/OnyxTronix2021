package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import pid.CtreMotionMagicController;
import pid.PIDFTerms;
import sensors.counter.CtreEncoder;

import static frc.robot.drivetrain.DriveTrainConstants.*;

public class DriveTrainComponentsBase implements DriveTrainComponents {

    WPI_TalonFX leftMasterMotor;
    WPI_TalonFX leftSlaveMotor;
    WPI_TalonFX rightMasterMotor;
    WPI_TalonFX rightSlaveMotor;
    DifferentialDrive differentialDrive;
    DifferentialDriveOdometry odometry;
    NormalizedPigeonIMU pigeonIMU;
    CtreMotionMagicController leftController;
    CtreMotionMagicController rightController;
    CtreEncoder leftEncoder;
    CtreEncoder rightEncoder;
    Field2d field2d;

    public DriveTrainComponentsBase() {
        leftMasterMotor = new WPI_TalonFX(3);
        leftMasterMotor.configFactoryDefault();
        leftMasterMotor.configAllSettings(getFalconConfiguration());
        leftMasterMotor.setNeutralMode(NeutralMode.Brake);
        leftMasterMotor.configOpenloopRamp(RAMP_TIME);

        leftSlaveMotor = new WPI_TalonFX(4);
        leftSlaveMotor.follow(leftMasterMotor);
        leftSlaveMotor.configAllSettings(getFalconConfiguration());
        leftSlaveMotor.setNeutralMode(NeutralMode.Brake);
        leftSlaveMotor.follow(leftMasterMotor);
        leftSlaveMotor.configOpenloopRamp(RAMP_TIME);

        rightMasterMotor = new WPI_TalonFX(1);
        rightMasterMotor.configFactoryDefault();
        rightMasterMotor.configAllSettings(getFalconConfiguration());
        rightMasterMotor.setInverted(true);
        rightMasterMotor.setNeutralMode(NeutralMode.Brake);
        rightMasterMotor.configOpenloopRamp(RAMP_TIME);

        rightSlaveMotor = new WPI_TalonFX(2);
        rightSlaveMotor.follow(rightMasterMotor);
        rightSlaveMotor.configAllSettings(getFalconConfiguration());
        rightSlaveMotor.setInverted(true);
        rightSlaveMotor.setNeutralMode(NeutralMode.Brake);
        rightSlaveMotor.follow(rightMasterMotor);
        rightSlaveMotor.configOpenloopRamp(RAMP_TIME);

        odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(0));
        odometry.resetPosition(new Pose2d(), new Rotation2d());

        pigeonIMU = new NormalizedPigeonIMU(PIGEON_PORT);

        differentialDrive = new DifferentialDrive(leftMasterMotor, rightMasterMotor);
        differentialDrive.setSafetyEnabled(false);

        leftEncoder = new CtreEncoder(leftMasterMotor);
        rightEncoder = new CtreEncoder(rightMasterMotor);

        leftController = new CtreMotionMagicController(leftMasterMotor, leftEncoder, new PIDFTerms(kP, kI, kD, kF),
                acceleration, cruiseVelocity, accelerationSmoothing);
        rightController = new CtreMotionMagicController(rightMasterMotor, rightEncoder, new PIDFTerms(kP, kI, kD, kF),
                acceleration, cruiseVelocity, accelerationSmoothing);

        field2d = new Field2d();
    }

    @Override
    public WPI_TalonFX getLeftMasterMotor() {
        return leftMasterMotor;
    }

    @Override
    public WPI_TalonFX getLeftSlaveMotor() {
        return leftSlaveMotor;
    }

    @Override
    public WPI_TalonFX getRightMasterMotor() {
        return rightMasterMotor;
    }

    @Override
    public WPI_TalonFX getRightSlaveMotor() {
        return rightSlaveMotor;
    }

    @Override
    public DifferentialDrive getDifferentialDrive() {
        return differentialDrive;
    }

    @Override
    public DifferentialDriveOdometry getOdometry() {
        return odometry;
    }

    @Override
    public NormalizedPigeonIMU getNormelizedPigeonIMU() {
        return pigeonIMU;
    }

    @Override
    public CtreEncoder getLeftEncoder() {
        return leftEncoder;
    }

    @Override
    public CtreEncoder getRightEncoder() {
        return rightEncoder;
    }

    @Override
    public CtreMotionMagicController getLeftController() {
        return leftController;
    }

    @Override
    public CtreMotionMagicController getRightController() {
        return rightController;
    }

    @Override
    public Field2d getField() {
        return field2d;
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

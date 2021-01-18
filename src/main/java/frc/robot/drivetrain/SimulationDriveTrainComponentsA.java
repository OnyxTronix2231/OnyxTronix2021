package frc.robot.drivetrain;

import static frc.robot.drivetrain.DriveTrainConstants.CONVERSION_RATE;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.CLOSED_LOOP_RAMP;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.CONTINUOUS_CURRENT_LIMIT;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.LEFT_MASTER_PORT;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.LEFT_SLAVE_PORT;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.MAX_OUTPUT_FORWARD;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.MAX_OUTPUT_REVERSE;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.OPEN_LOOP_RAMP;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.PEAK_CURRENT_DURATION;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.RIGHT_MASTER_PORT;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.RIGHT_SLAVE_PORT;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.MAX_ACCELERATION_METERS_PER_SECOND_SQUARED;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.MAX_SPEED_METERS_PER_SECOND;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.MAX_VOLTAGE;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.TRACKWIDTH_METERS;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.VOLTS;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.VOLT_SECONDS_PER_METER;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.VOLT_SECONDS_SQUARED_PER_METER;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.simulation.AnalogGyroSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.system.plant.DCMotor;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;

public class SimulationDriveTrainComponentsA implements SimulationDriveTrainComponents{

  private final WPI_TalonSRX rightMaster;
  private final WPI_TalonSRX rightSlave;
  private final WPI_TalonSRX leftMaster;
  private final WPI_TalonSRX leftSlave;
  private final AnalogGyroSim analogGyroSim;

  private final Field2d field2d;

  public SimulationDriveTrainComponentsA(){
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

    leftMaster = new WPI_TalonSRX(LEFT_MASTER_PORT);
    leftMaster.configFactoryDefault();
    leftMaster.configAllSettings(getSRXConfiguration());
    leftMaster.setNeutralMode(NeutralMode.Brake);

    leftSlave = new WPI_TalonSRX(LEFT_SLAVE_PORT);
    leftSlave.configFactoryDefault();
    leftSlave.configAllSettings(getSRXConfiguration());
    leftSlave.setNeutralMode(NeutralMode.Brake);
    leftSlave.follow(leftMaster);

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

  public Field2d getField2d() {
    return field2d;
  }
}

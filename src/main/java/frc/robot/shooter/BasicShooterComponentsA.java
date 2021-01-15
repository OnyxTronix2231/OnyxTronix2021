package frc.robot.shooter;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.simulation.LinearSystemSim;
import edu.wpi.first.wpilibj.system.LinearSystem;
import edu.wpi.first.wpilibj.system.plant.DCMotor;
import edu.wpi.first.wpilibj.system.plant.LinearSystemId;
import edu.wpi.first.wpiutil.math.numbers.N1;
import edu.wpi.first.wpiutil.math.numbers.N2;
import pid.CtreMotionMagicController;
import pid.CtrePIDController;
import pid.PIDControlMode;
import pid.PIDFTerms;
import sensors.counter.CtreEncoder;

import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.*;

public class BasicShooterComponentsA implements ShooterComponents {

    private final WPI_TalonSRX masterMotor;
    private final WPI_TalonSRX slaveMotor;
    private final WPI_TalonSRX angleMotor;
    private final CtreMotionMagicController ctreMotionMagicController;
    private final CtrePIDController ctrePIDController;
    private final FlywheelSim flywheelSim;
    private final LinearSystem<N2, N1, N1> linearSystemSim;

    public BasicShooterComponentsA() {
        masterMotor = new WPI_TalonSRX(MASTER_MOTOR_ID);
        masterMotor.configFactoryDefault();
       // masterMotor.configAllSettings(getFalconConfiguration());
        masterMotor.setNeutralMode(NeutralMode.Coast);
        masterMotor.setInverted(false);

        slaveMotor = new WPI_TalonSRX(SLAVE_MOTOR_ID);
       // slaveMotor.configAllSettings(getFalconConfiguration());
        slaveMotor.configFactoryDefault();
        slaveMotor.setNeutralMode(NeutralMode.Coast);
        slaveMotor.follow(masterMotor);

        angleMotor = new WPI_TalonSRX(ANGLE_MOTOR_ID);
        angleMotor.configFactoryDefault();
        angleMotor.setNeutralMode(NeutralMode.Brake);
        angleMotor.configAllSettings(getTalonSRXConfiguration());

         ctreMotionMagicController = new CtreMotionMagicController(angleMotor,
                new CtreEncoder(angleMotor),
                new PIDFTerms(ANGLE_MOTOR_VELOCITY_P, ANGLE_MOTOR_VELOCITY_I, ANGLE_MOTOR_VELOCITY_D, ANGLE_MOTOR_VELOCITY_F),
                ANGLE_MOTOR_MAX_ACCELERATION, ANGLE_MOTOR_CRUISE_VELOCITY, ANGLE_MOTOR_ACCELERATION_SMOOTHING);
         ctrePIDController = new CtrePIDController(masterMotor,
                new CtreEncoder(masterMotor),
                new PIDFTerms(SHOOTER_VELOCITY_P, SHOOTER_VELOCITY_I, SHOOTER_VELOCITY_D, SHOOTER_VELOCITY_F), PIDControlMode.Velocity);
        flywheelSim = new FlywheelSim(DCMotor.getFalcon500(2), 1, 0.01 );
        linearSystemSim = LinearSystemId.identifyPositionSystem(1.0,1.0);
    }

//    private TalonFXConfiguration getFalconConfiguration() {
//        final TalonFXConfiguration config = new TalonFXConfiguration();
//        config.supplyCurrLimit.currentLimit = CURRENT_LIMIT;
//        config.supplyCurrLimit.triggerThresholdCurrent = TRIGGER_THRESHOLD_CURRENT;
//        config.supplyCurrLimit.triggerThresholdTime = TRIGGER_THRESHOLD_TIME;
//        config.closedloopRamp = SHOOTER_MOTOR_CLOSE_LOOP_RAMP;
//        config.openloopRamp = SHOOTER_MOTOR_OPEN_LOOP_RAMP;
//        config.supplyCurrLimit.enable = true;
//        return config;
//    }

    private TalonSRXConfiguration getTalonSRXConfiguration() {
        final TalonSRXConfiguration config = new TalonSRXConfiguration();
        config.openloopRamp = ANGLE_MOTOR_OPEN_LOOP_RAMP;
        config.closedloopRamp = ANGLE_MOTOR_CLOSE_LOOP_RAMP;
        config.continuousCurrentLimit = ANGLE_MOTOR_CONTINUOUS_CURRENT_LIMIT;
        config.peakCurrentDuration = ANGLE_MOTOR_PEAK_AMP_DURATION;
        config.peakCurrentLimit = ANGLE_MOTOR_PEAK_AMP;
        return config;
    }

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public WPI_TalonSRX getAngleMotor() {
        return angleMotor;
    }

    @Override
    public CtreMotionMagicController getCtreMotionMagicController() {
        return ctreMotionMagicController;
    }

    @Override
    public CtrePIDController getCtrePIDController() {
        return ctrePIDController;
    }

    @Override
    public IMotorController getSlaveMotor() {
        return slaveMotor;
    }

    @Override
    public FlywheelSim getFlyWheelSim() {
        return flywheelSim;
    }
}

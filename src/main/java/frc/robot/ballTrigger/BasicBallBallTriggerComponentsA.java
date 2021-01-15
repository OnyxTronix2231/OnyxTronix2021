package frc.robot.ballTrigger;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import pid.CtrePIDController;
import pid.PIDControlMode;
import sensors.counter.CtreEncoder;

import static frc.robot.ballTrigger.BallTriggerConstants.BallTriggerConstantsA.MASTER_MOTOR_ID;
import static frc.robot.ballTrigger.BallTriggerConstants.BallTriggerConstantsA.SOLENOID_CHANNEL;
import static frc.robot.ballTrigger.BallTriggerConstants.BallTriggerConstantsA.VELOCITY_P;
import static frc.robot.ballTrigger.BallTriggerConstants.BallTriggerConstantsA.VELOCITY_I;
import static frc.robot.ballTrigger.BallTriggerConstants.BallTriggerConstantsA.VELOCITY_D;
import static frc.robot.ballTrigger.BallTriggerConstants.BallTriggerConstantsA.VELOCITY_F;
import static frc.robot.ballTrigger.BallTriggerConstants.BallTriggerConstantsA.PEAK_AMP;
import static frc.robot.ballTrigger.BallTriggerConstants.BallTriggerConstantsA.PEAK_AMP_DURATION;
import static frc.robot.ballTrigger.BallTriggerConstants.BallTriggerConstantsA.CONTINUOUS_CURRENT_LIMIT;
import static frc.robot.ballTrigger.BallTriggerConstants.BallTriggerConstantsA.OPEN_LOOP_RAMP;
import static frc.robot.ballTrigger.BallTriggerConstants.BallTriggerConstantsA.CLOSED_LOOP_RAMP;

public class BasicBallBallTriggerComponentsA implements BallTriggerComponents {

    private final WPI_TalonSRX masterMotor;
    private final Solenoid solenoid;
    private final CtrePIDController pidController;
    private final CtreEncoder encoder;

    public BasicBallBallTriggerComponentsA() {
        masterMotor = new WPI_TalonSRX(MASTER_MOTOR_ID);
        masterMotor.configFactoryDefault();
        masterMotor.setNeutralMode(NeutralMode.Brake);
        masterMotor.enableCurrentLimit(true);

        solenoid = new Solenoid(SOLENOID_CHANNEL);

        encoder = new CtreEncoder(masterMotor, 0);

        pidController = new CtrePIDController(masterMotor, encoder, VELOCITY_P, VELOCITY_I, VELOCITY_D, VELOCITY_F,
                PIDControlMode.Velocity);
    }

    private TalonSRXConfiguration getConfiguration() {
        final TalonSRXConfiguration config = new TalonSRXConfiguration();
        config.peakCurrentLimit = PEAK_AMP;
        config.peakCurrentDuration = PEAK_AMP_DURATION;
        config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;
        config.openloopRamp = OPEN_LOOP_RAMP;
        config.closedloopRamp = CLOSED_LOOP_RAMP;
        return config;
    }

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public Solenoid getSolenoid() {
        return solenoid;
    }

    @Override
    public CtreEncoder getEncoder() {
        return encoder;
    }

    @Override
    public CtrePIDController getPIDController() {
        return pidController;
    }
}

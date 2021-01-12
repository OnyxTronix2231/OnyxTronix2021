package frc.robot.ballCollector;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;

import static frc.robot.ballCollector.BallCollectorConstants.BallCollectorConstantsA.MASTER_MOTOR_ID;
import static frc.robot.ballCollector.BallCollectorConstants.BallCollectorConstantsA.SOLENOID_ID;

public class BasicBallCollectorComponentsA implements BallCollectorComponents {

    private final WPI_TalonSRX masterMotor;
    private final Solenoid solenoid;

    public BasicBallCollectorComponentsA() {
        masterMotor = new WPI_TalonSRX(MASTER_MOTOR_ID);
        masterMotor.configFactoryDefault();
        masterMotor.setNeutralMode(NeutralMode.Brake);
        masterMotor.enableCurrentLimit(true);

        solenoid = new Solenoid(SOLENOID_ID);
    }

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return null;
    }

    @Override
    public Solenoid getSolenoid() {
        return null;
    }
}

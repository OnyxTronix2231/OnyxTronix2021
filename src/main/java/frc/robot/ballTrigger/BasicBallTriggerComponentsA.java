package frc.robot.ballTrigger;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;

import static frc.robot.ballTrigger.BallTriggerConstants.BallTriggerConstantsA.MASTER_MOTOR_ID;
import static frc.robot.ballTrigger.BallTriggerConstants.BallTriggerConstantsA.SOLENOID_ID;

public class BasicBallTriggerComponentsA implements BallTriggerComponents {

    private final WPI_TalonSRX masterMotor;

    private final Solenoid solenoid;

    public BasicBallTriggerComponentsA() {
        masterMotor = new WPI_TalonSRX(MASTER_MOTOR_ID);
        masterMotor.configFactoryDefault();
        masterMotor.setNeutralMode(NeutralMode.Coast);
        masterMotor.enableCurrentLimit(true);

        solenoid = new Solenoid(SOLENOID_ID);
    }

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public Solenoid getSolenoid() {
        return solenoid;
    }
}

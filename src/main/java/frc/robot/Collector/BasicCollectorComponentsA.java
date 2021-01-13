package frc.robot.Collector;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;

import static frc.robot.Collector.CollectorConstants.BallCollectorConstantsA.MASTER_MOTOR_ID;
import static frc.robot.Collector.CollectorConstants.BallCollectorConstantsA.SOLENOID_CHANNEL;

public class BasicCollectorComponentsA implements CollectorComponents {

    private final WPI_TalonSRX masterMotor;
    private final Solenoid solenoid;

    public BasicCollectorComponentsA() {
        masterMotor = new WPI_TalonSRX(MASTER_MOTOR_ID);
        masterMotor.configFactoryDefault();
        masterMotor.setNeutralMode(NeutralMode.Brake);
        masterMotor.enableCurrentLimit(true);

        solenoid = new Solenoid(SOLENOID_CHANNEL);
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

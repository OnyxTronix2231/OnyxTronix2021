package frc.robot.revolver;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import pid.CtrePIDController;
import pid.PIDControlMode;
import sensors.counter.CtreEncoder;

import static frc.robot.revolver.RevolverConstants.RevolverComponentsA.MASTER_MOTOR_ID;

public class BasicRevolverComponentsA implements RevolverComponents {

    private final WPI_TalonFX masterMotor;
    private final CtrePIDController pidController;
    private final CtreEncoder encoder;

    public BasicRevolverComponentsA() {
        masterMotor = new WPI_TalonFX(MASTER_MOTOR_ID);
        masterMotor.configFactoryDefault();
        masterMotor.setNeutralMode(NeutralMode.Brake);

        encoder = new CtreEncoder(masterMotor, 0);

        pidController = new CtrePIDController(masterMotor, encoder, 0, 0, 0, 0, PIDControlMode.Velocity);
    }

    @Override
    public WPI_TalonFX getMasterMotor() {
        return masterMotor;
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

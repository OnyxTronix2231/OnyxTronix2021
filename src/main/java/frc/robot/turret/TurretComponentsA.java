package frc.robot.turret;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import pid.CtrePIDController;
import pid.PIDControlMode;
import pid.interfaces.PIDController;
import sensors.counter.CtreEncoder;

import static frc.robot.turret.TurretConstants.*;
import static frc.robot.turret.TurretConstants.TurretComponentsA.*;


public class TurretComponentsA implements TurretComponents {
    private WPI_TalonSRX masterMotor;
    private CtreEncoder encoder;
    private PIDController controller;

    public TurretComponentsA() {
        masterMotor = new WPI_TalonSRX(MASTER_MOTOR_ID);
        encoder = new CtreEncoder(masterMotor, PID_IDX);
        controller = new CtrePIDController(masterMotor, encoder, KP, KI, KD, KF, PIDControlMode.Position);
    }

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public CtreEncoder getEncoder() {
        return encoder;
    }

    @Override
    public PIDController getPIDController() {
        return controller;
    }
}

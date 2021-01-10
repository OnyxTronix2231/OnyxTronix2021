package frc.robot.turret;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import pid.CtrePIDController;
import pid.interfaces.PIDController;
import sensors.counter.CtreEncoder;

import static frc.robot.turret.TurretConstants.*;
import static frc.robot.turret.TurretConstants.TurretComponentsA.PID_IDX;


public class TurretComponentsA implements TurretComponents {
    private WPI_TalonSRX masterMotor;
    private CtreEncoder encoder;
    private PIDController controller;

    public TurretComponentsA() {
        this.masterMotor = new WPI_TalonSRX(MASTER_MOTOR_ID);
        this.encoder = new CtreEncoder(masterMotor, PID_IDX);
        this.controller = new CtrePIDController(masterMotor,encoder,0,0,0,0,0,0);
    }

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public CtreEncoder getEncoder() { return encoder; }

    @Override
    public PIDController PID_CONTROLLER() {
        return null;
    }

}

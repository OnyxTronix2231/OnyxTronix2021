package frc.robot.turret;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import pid.CtreMotionMagicController;
import pid.CtrePIDController;
import pid.PIDControlMode;
import sensors.counter.CtreEncoder;

import static frc.robot.turret.TurretConstants.*;
import static frc.robot.turret.TurretConstants.TurretComponentsA.*;


public class TurretComponentsA implements TurretComponents {
    private WPI_TalonSRX masterMotor;
    private CtreEncoder encoder;
    private CtreMotionMagicController controller;

    public TurretComponentsA() {
        masterMotor = new WPI_TalonSRX(MASTER_MOTOR_ID);
        encoder = new CtreEncoder(masterMotor, PID_IDX);

        controller = new CtreMotionMagicController(masterMotor, encoder, KP, KI, KD, KF, ACCELERATION,
                CRUISE_VELOCITY, 0);
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
    public CtreMotionMagicController getController() {
        return controller;
    }
}

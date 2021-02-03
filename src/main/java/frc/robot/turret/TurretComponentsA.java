package frc.robot.turret;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import pid.CtreMotionMagicController;
import pid.CtrePIDController;
import pid.PIDControlMode;
import pid.interfaces.MotionMagicController;
import sensors.counter.Counter;
import sensors.counter.CtreEncoder;
import static frc.robot.turret.TurretConstants.*;
import static frc.robot.turret.TurretConstants.TurretComponentsA.*;

public class TurretComponentsA implements TurretComponents {

    private final WPI_TalonSRX masterMotor;
    private final CtreEncoder encoder;
    private final CtreMotionMagicController controller;

    public TurretComponentsA() {
        masterMotor = new WPI_TalonSRX(MASTER_MOTOR_ID);
        encoder = new CtreEncoder(masterMotor);
        controller = new CtreMotionMagicController(masterMotor, encoder, KP, KI, KD, KF, ACCELERATION,
                CRUISE_VELOCITY, ACCELERATION_SMOOTHING);
    }

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public Counter getEncoder() {
        return encoder;
    }

    @Override
    public MotionMagicController getTurretController() {
        return controller;
    }
}

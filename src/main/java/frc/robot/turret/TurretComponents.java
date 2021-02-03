package frc.robot.turret;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import pid.CtreMotionMagicController;
import pid.interfaces.MotionMagicController;
import pid.interfaces.PIDController;
import sensors.Switch.Switch;
import sensors.counter.Counter;
import sensors.counter.CtreEncoder;

public interface TurretComponents {

    WPI_TalonSRX getMasterMotor();

    Counter getEncoder();

    MotionMagicController getTurretController();
}

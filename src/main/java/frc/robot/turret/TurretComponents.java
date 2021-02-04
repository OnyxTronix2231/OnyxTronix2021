package frc.robot.turret;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import pid.interfaces.MotionMagicController;
import sensors.counter.Counter;

public interface TurretComponents {

    WPI_TalonSRX getMotor();

    MotionMagicController getTurretController();

    Counter getEncoder();
}

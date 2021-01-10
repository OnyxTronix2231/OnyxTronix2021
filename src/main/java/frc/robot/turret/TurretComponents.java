package frc.robot.turret;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import pid.interfaces.PIDController;
import sensors.Switch.Switch;
import sensors.counter.CtreEncoder;

public interface TurretComponents {

    WPI_TalonSRX getMasterMotor();

    CtreEncoder getEncoder();

    PIDController PID_CONTROLLER();
}

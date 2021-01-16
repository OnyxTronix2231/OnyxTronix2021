package frc.robot.revolver;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import pid.interfaces.PIDController;
import sensors.counter.Counter;

public interface RevolverComponents {

    WPI_TalonFX getMasterMotor();

    Counter getEncoder();

    PIDController getPIDController();
}

package frc.robot.flywheel;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import pid.interfaces.PIDController;
import sensors.counter.Counter;

public interface FlywheelComponents {

    WPI_TalonFX getMasterMotor();

    IMotorController getSlaveMotor();

    Counter getEncoder();

    PIDController getController();
}

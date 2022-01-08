package frc.robot.climber;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import pid.CtreController;
import sensors.counter.Counter;

public interface ClimberComponents {

    WPI_TalonFX getMasterMotor();

    WPI_TalonFX getSlaveMotor();

    CtreController getController();

    Counter getEncoder();
}

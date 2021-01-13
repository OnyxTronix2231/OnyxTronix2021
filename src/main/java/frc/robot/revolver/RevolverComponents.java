package frc.robot.revolver;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import pid.CtrePIDController;
import sensors.counter.CtreEncoder;

public interface RevolverComponents {

    WPI_TalonFX getMasterMotor();

    CtreEncoder getEncoder();

    CtrePIDController getPIDController();
}

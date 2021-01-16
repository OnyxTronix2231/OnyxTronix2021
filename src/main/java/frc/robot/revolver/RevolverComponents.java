package frc.robot.revolver;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import pid.interfaces.PIDController;
import sensors.counter.Counter;

public interface RevolverComponents {

    WPI_TalonSRX getMasterMotor();

    Counter getEncoder();

    PIDController getPIDController();

    FlywheelSim getFlyWheelSim();
}

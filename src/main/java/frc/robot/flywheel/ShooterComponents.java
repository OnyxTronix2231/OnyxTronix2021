package frc.robot.flywheel;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import pid.interfaces.PIDController;
import sensors.counter.Counter;

public interface ShooterComponents {

    WPI_TalonSRX getMasterMotor();

    IMotorController getSlaveMotor();

    Counter getShooterEncoder();

    PIDController getShooterController();

    FlywheelSim getFlyWheelSim();
}

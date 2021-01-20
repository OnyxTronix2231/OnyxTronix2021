package frc.robot.shooter;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import pid.interfaces.MotionMagicController;
import pid.interfaces.PIDController;
import sensors.counter.Counter;

public interface ShooterComponents {

    WPI_TalonFX getMasterShooterMotor();

    IMotorController getSlaveShooterMotor();

    Counter getShooterMotorEncoder();

    WPI_TalonSRX getAngularMotor();

    Counter getAngularMotorEncoder();

    PIDController getShooterController();

    MotionMagicController getAngularController();
}

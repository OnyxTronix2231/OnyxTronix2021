package frc.robot.shooter;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import pid.interfaces.MotionMagicController;
import pid.interfaces.PIDController;

public interface ShooterComponents {

    WPI_TalonFX getMasterMotor();

    WPI_TalonSRX getAngleMotor();

    IMotorController getSlaveMotor();

    MotionMagicController getCtreMotionMagicController();

    PIDController getCtrePIDController();

}

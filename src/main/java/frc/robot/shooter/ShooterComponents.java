package frc.robot.shooter;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import pid.CtreMotionMagicController;
import pid.CtrePIDController;

public interface ShooterComponents {

    WPI_TalonSRX getMasterMotor();

    WPI_TalonSRX getAngleMotor();

    CtreMotionMagicController getCtreMotionMagicController();

    CtrePIDController getCtrePIDController();

    IMotorController getSlaveMotor();

    FlywheelSim getFlyWheelSim();
}

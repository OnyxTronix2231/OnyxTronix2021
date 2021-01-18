package frc.robot.shooter;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.simulation.LinearSystemSim;
import pid.interfaces.MotionMagicController;
import pid.interfaces.PIDController;

public interface ShooterComponents {

    WPI_TalonSRX getMasterMotor();

    WPI_TalonSRX getAngleMotor();

    IMotorController getSlaveMotor();

    MotionMagicController getCtreMotionMagicController();

    PIDController getCtrePIDController();

    FlywheelSim getFlyWheelSim();

    LinearSystemSim getLinearSystemSim();
}

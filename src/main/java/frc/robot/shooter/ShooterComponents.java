package frc.robot.shooter;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.Solenoid;
import pid.CtreMotionMagicController;
import pid.CtrePIDController;

public interface ShooterComponents {

    WPI_TalonFX getMasterMotor();

    TalonSRX getAngleMotor();

    CtreMotionMagicController getCtreMotionMagicController();

    CtrePIDController getCtrePIDController();

    IMotorController getSlaveMotor();
}

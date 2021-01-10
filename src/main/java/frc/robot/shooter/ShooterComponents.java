package frc.robot.shooter;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.Solenoid;

public interface ShooterComponents {

  WPI_TalonFX getMasterMotor();

  IMotorController getSlaveMotor();

  Solenoid getSolenoid();
}

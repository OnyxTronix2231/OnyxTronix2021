package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public interface DriveTrainComponents {

  NormalizedPigeonIMU getPigeonIMU();

  WPI_TalonFX getRightMasterMotor();

  IMotorController getRightSlaveMotor();

  WPI_TalonFX getLeftMasterMotor();

  IMotorController getLeftSlaveMotor();
}

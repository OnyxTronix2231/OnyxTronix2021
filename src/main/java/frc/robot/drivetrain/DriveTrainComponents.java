package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.drivetrain.utils.NormalizedPigeonIMU;

public interface DriveTrainComponents {

    WPI_TalonFX getLeftMasterMotor();

    IMotorController getLeftSlaveMotor();

    WPI_TalonFX getRightMasterMotor();

    IMotorController getRightSlaveMotor();

    NormalizedPigeonIMU getNormelizedPigeonIMU();
}

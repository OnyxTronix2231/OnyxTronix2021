package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.drivetrain.utils.NormalizedPigeonIMU;

public interface DriveTrainComponents {

    SpeedControllerGroup getLeftMotors();

    SpeedControllerGroup getRightMotors();

    WPI_TalonFX getLeftMasterMotor();

    IMotorController getLeftSlaveMotor();

    WPI_TalonFX getRightMasterMotor();

    IMotorController getRightSlaveMotor();

    NormalizedPigeonIMU getNormelizedPigeonIMU();
}

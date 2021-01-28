package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public interface DriveTrainComponents {

    SpeedController getLeftMotors();

    SpeedController getRightMotors();

    WPI_TalonFX getLeftMasterMotor();

    IMotorController getLeftSlaveMotor();

    WPI_TalonFX getRightMasterMotor();

    IMotorController getRightSlaveMotor();

    DifferentialDrive getDifferentialDrive();

    NormalizedPigeonIMU getNormelizedPigeonIMU();
}

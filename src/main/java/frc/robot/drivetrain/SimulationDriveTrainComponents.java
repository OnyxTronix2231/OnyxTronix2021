package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.simulation.AnalogGyroSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;

public interface SimulationDriveTrainComponents {
    AnalogGyroSim getAnalogGyroSim();

    WPI_TalonSRX getRightMasterMotor();

    IMotorController getRightSlaveMotor();

    SpeedController getRightMotors();

    WPI_TalonSRX getLeftMasterMotor();

    IMotorController getLeftSlaveMotor();

    SpeedController getLeftMotors();

    Field2d getField2d();
}

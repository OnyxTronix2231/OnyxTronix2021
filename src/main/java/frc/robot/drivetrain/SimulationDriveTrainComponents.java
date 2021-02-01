package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.simulation.AnalogGyroSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;

public interface SimulationDriveTrainComponents {
    SpeedControllerGroup getLeftMotors();

    SpeedControllerGroup getRightMotors();

    WPI_TalonSRX getLeftMasterMotor();

    IMotorController getLeftSlaveMotor();

    WPI_TalonSRX getRightMasterMotor();

    IMotorController getRightSlaveMotor();

    AnalogGyroSim getAnalogGyroSim();

    Field2d getField2d();
}

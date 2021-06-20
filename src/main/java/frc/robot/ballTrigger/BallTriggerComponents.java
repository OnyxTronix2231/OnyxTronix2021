package frc.robot.ballTrigger;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import pid.interfaces.PIDController;
import sensors.counter.Counter;

public interface BallTriggerComponents {

    WPI_TalonSRX getMasterMotor();

    IMotorController getSlaveMotor();

    DoubleSolenoid getSolenoid();

    Counter getEncoder();

    PIDController getPIDController();
}

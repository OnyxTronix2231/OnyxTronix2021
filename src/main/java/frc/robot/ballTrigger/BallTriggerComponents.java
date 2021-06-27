package frc.robot.ballTrigger;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import pid.interfaces.PIDController;
import sensors.counter.Counter;

public interface BallTriggerComponents {

    WPI_TalonSRX getMasterMotor();

    WPI_TalonSRX getSlaveMotor();

    DoubleSolenoid getSolenoid();

    Counter getEncoder();

    PIDController getPIDController();
}

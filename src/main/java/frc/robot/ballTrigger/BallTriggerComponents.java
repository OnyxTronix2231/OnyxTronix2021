package frc.robot.ballTrigger;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import pid.interfaces.PIDController;
import sensors.counter.Counter;

public interface BallTriggerComponents {

    WPI_TalonSRX getMotor();

    Solenoid getSolenoid();

    Counter getEncoder();

    PIDController getPIDController();
}

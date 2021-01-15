package frc.robot.ballTrigger;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import pid.CtrePIDController;
import sensors.counter.CtreEncoder;

public interface BallTriggerComponents {

    WPI_TalonSRX getMasterMotor();

    Solenoid getSolenoid();

    CtreEncoder getEncoder();

    CtrePIDController getPIDController();
}

package frc.robot.revolver;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import pid.interfaces.PIDController;
import sensors.counter.Counter;

public interface RevolverComponents {

    WPI_TalonFX getMotor();

    Counter getEncoder();

    PIDController getPIDController();

    DigitalInput getHallEffect();
}

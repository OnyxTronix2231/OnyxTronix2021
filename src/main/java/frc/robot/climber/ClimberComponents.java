package frc.robot.climber;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.Solenoid;
import pid.CtreController;
import sensors.counter.CtreEncoder;

public interface ClimberComponents {
    WPI_TalonFX getRightMotor();

    WPI_TalonFX getLeftMotor();

    CtreController getController();

    CtreEncoder getEncoder();

}

/// farfaW
/// smileW
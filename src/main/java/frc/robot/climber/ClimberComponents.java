package frc.robot.climber;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.Solenoid;

public interface ClimberComponents {
    WPI_TalonFX getMasterMotor();

    WPI_TalonFX getSlaveMotor();

    Solenoid getSolenoid();
}

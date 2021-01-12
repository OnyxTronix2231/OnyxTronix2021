package frc.robot.Roulette;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

public interface RouletteComponents {
    WPI_TalonSRX getMasterMotor();
    Solenoid getSolenoid();

}

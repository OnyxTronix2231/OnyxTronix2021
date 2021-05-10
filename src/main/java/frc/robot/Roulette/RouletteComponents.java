package frc.robot.Roulette;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.Solenoid;
import pid.interfaces.MotionMagicController;
import sensors.counter.Counter;


public interface RouletteComponents {

    WPI_TalonSRX getMasterMotor();

    Solenoid getSolenoid();

    MotionMagicController getController();

    Counter getEncoder();

    ColorSensorV3 getColorSensor();

}

package frc.robot.revolver;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.Rev2mDistanceSensor;

public interface RevolverComponents {

    WPI_TalonFX getMasterMotor();
}

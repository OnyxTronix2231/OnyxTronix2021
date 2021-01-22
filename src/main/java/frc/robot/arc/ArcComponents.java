package frc.robot.arc;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import pid.interfaces.MotionMagicController;
import sensors.counter.Counter;

public interface ArcComponents {

    WPI_TalonSRX getMasterMotor();

    Counter getArcMotorEncoder();

    MotionMagicController getArcController();
}

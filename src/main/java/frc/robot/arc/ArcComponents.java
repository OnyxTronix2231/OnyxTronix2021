package frc.robot.arc;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import pid.interfaces.MotionMagicController;
import sensors.Switch.TalonSrxForwardMicroswitch;
import sensors.Switch.TalonSrxReverseMicroswitch;
import sensors.counter.Counter;

public interface ArcComponents {

    WPI_TalonSRX getMasterMotor();

    Counter getEncoder();

    MotionMagicController getController();

    TalonSrxReverseMicroswitch getReverseLimitSwitch();

    TalonSrxForwardMicroswitch getForwardLimitSwitch();
}

package frc.robot.arc;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.simulation.LinearSystemSim;
import pid.interfaces.MotionMagicController;
import sensors.counter.Counter;

public interface ArcComponents {

    WPI_TalonSRX getMasterMotor();

    Counter getEncoder();

    MotionMagicController getController();

    LinearSystemSim getLinearSystemSim();
}

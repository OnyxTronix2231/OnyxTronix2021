package frc.robot.turret;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.math.geometry.Pose2d;
import pid.interfaces.MotionMagicController;
import sensors.counter.Counter;

public interface TurretComponents {

    WPI_TalonSRX getMotor();

    MotionMagicController getTurretController();

    Counter getEncoder();

    Pose2d getCurrentPos();

    void setCurrentPos(Pose2d pose2d);
}

package frc.robot.vision;

import vision.limelight.Limelight;
import vision.limelight.target.LimelightTarget;

import java.util.function.DoubleSupplier;

public abstract class VisionTarget {
    /*a value given by the limelight itself- the vertical angle offset from the target to the crosshair*/
    protected double verticalAngleLimelightToTarget;

    /*a calculated value- the horizontal angle offset between the target direction to the field (gyro 0)*/
    protected double horizontalAngleTargetToRobot;

    /*a calculated value- the horizontal air distance from the turret center to the target*/
    protected double airDistanceTurretToTarget;

    /*a calculated value- the horizontal angle offset from the vector of the target to the turret direction*/
    protected double horizontalAngleTargetToTurret;

    /*a calculated vector- the vector that is connected from the turret center to the target*/
    protected Vector2dEx RTFVectorTurretToTarget;

    /*a calculated vector- the vector that is connected from the robot center to the target*/
    protected Vector2dEx RTFVectorRobotToTarget;

    protected DoubleSupplier gyroYawAngle;
    protected DoubleSupplier turretAngleRTF;
    protected Limelight limelight;

    public abstract double getVerticalAngleLimelightToTarget();

    public abstract double getHorizontalAngleTargetToRobot();

    public abstract double getAirDistanceTurretToTarget();

    public abstract double getHorizontalAngleTargetToTurret();

    public abstract Vector2dEx getRTFVectorTurretToTarget();

    public abstract Vector2dEx getRTFVectorRobotToTarget();
}

package frc.robot.vision.visionMainChallenge;

import frc.robot.vision.Vector2dEx;
import vision.limelight.Limelight;
import java.util.function.DoubleSupplier;

public abstract class VisionTarget {

    /*a saved variable - the limelight give us all the basic information*/
    protected Limelight limelight;

    /*a saved supplier - the gyro yaw angle from the rotation we initialized the gyro (0 degrees)*/
    protected DoubleSupplier gyroYawAngle;

    /*a saved supplier - the rotation angle that the turret made relative to the robot (field)*/
    protected DoubleSupplier turretAngleRTR;

    /*a calculated vector- the vector that is connected from the turret center to the target*/
    protected Vector2dEx vectorTurretToTargetRTF;

    /*a calculated vector- the vector that is connected from the robot center to the target*/
    protected Vector2dEx vectorRobotToTargetRTF;

    /*a value given by the limelight itself- the vertical angle offset from the target to the crosshair*/
    protected double verticalAngleLimelightToTarget;

    /*a calculated value- the horizontal angle offset between the target direction to the field (gyro 0)*/
    protected double horizontalAngleTargetToRobot;

    /*a calculated value- the horizontal air distance from the turret center to the target*/
    protected double airDistanceTurretToTarget;

    /*a calculated value- the horizontal angle offset from the vector of the target to the turret direction*/
    protected double horizontalAngleTargetToTurret;

    public abstract void update();

    public abstract double getVerticalAngleLimelightToTarget();

    public abstract double getHorizontalAngleTargetToRobot();

    public abstract double getAirDistanceTurretToTarget();

    public abstract double getHorizontalAngleTargetToTurret();

    public abstract Vector2dEx getVectorTurretToTargetRTF();

    public abstract Vector2dEx getVectorRobotToTargetRTF();
}

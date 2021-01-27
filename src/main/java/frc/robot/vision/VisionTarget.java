package frc.robot.vision;

public abstract class VisionTarget {
    /*a value given by the limelight itself- the vertical angle offset from the target to the crosshair*/
    protected double verticalAngleOffsetLimelightToTarget;

    /*a calculated value- the horizontal angle offset between the target direction to the field (gyro 0)*/
    protected double TargetToFieldHorizontalAngleOffset;

    /*a calculated value- the horizontal air distance from the turret center to the target*/
    protected double airDistanceTurretToTarget;

    /*a calculated value- the horizontal angle offset from the vector of the target to the turret direction*/
    protected double angleOffsetTargetToTurret;

    /*a calculated vector- the vector that is connected from the turret center to the target*/
    protected Vector2dEx turretToTargetVectorRTF;

    /*a calculated vector- the vector that is connected from the robot center to the target*/
    protected Vector2dEx robotToTargetVector;

    public VisionTarget(){}

    public abstract double getVerticalAngleOffsetLimelightToTarget();

    public abstract double getTargetToFieldHorizontalAngleOffset();

    public abstract double getAirDistanceTurretToTarget();

    public abstract double getAngleOffsetTargetToTurret();

    public abstract Vector2dEx getTurretToTargetVectorRTF();

    public abstract Vector2dEx getRobotToTargetVector();
}

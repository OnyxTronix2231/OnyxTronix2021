package frc.robot.vision;

import static frc.robot.vision.VisionConstants.*;

public class InnerTarget {

    /*a value given by the limelight itself- the vertical angle offset from the target to the crosshair*/
    private double verticalAngleOffsetLimelightToTarget;

    /*a calculated value- the horizontal angle offset between the target direction to the field (gyro 0)*/
    private double TargetToFieldHorizontalAngleOffset;

    /*a calculated value- the horizontal air distance from the turret center to the target*/
    private double airDistanceTurretToTarget;

    /*a calculated value- the horizontal angle offset from the vector of the target to the turret direction*/
    private double angleOffsetTargetToTurret;

    /*a calculated vector- the vector that is connected from the turret center to the target*/
    private Vector2dEx turretToTargetVectorRTF;

    /*a calculated vector- the vector that is connected from the robot center to the target*/
    private Vector2dEx robotToTargetVector;

    InnerTarget(double gyroYaw ,OuterTarget outerTarget) {
        /*init*/
        update(gyroYaw, outerTarget);
    }

    public void update(double gyroYaw ,OuterTarget outerTarget){

        /*calculating the vector that connects the turret center to the inner target
        * we are connecting the vector from the turret center to the outer target
        * to the fixed vector from the outer target to the inner one*/
        turretToTargetVectorRTF = outerTarget.getTurretToTargetVectorRTF();
        /*this is a vector addition and NOT a numeric addition of the vector values*/
        turretToTargetVectorRTF.add(VECTOR_OUTER_INNER_TARGET);

        /*getting the angle between the turret */
        TargetToFieldHorizontalAngleOffset = turretToTargetVectorRTF.direction();
        double angleDifferenceOuterToInner = TargetToFieldHorizontalAngleOffset - outerTarget.getTargetToFieldHorizontalAngleOffset();
        angleOffsetTargetToTurret = outerTarget.getAngleOffsetTargetToTurret() - angleDifferenceOuterToInner;

        Vector2dEx limelightCenterToTargetVector = Vector2dEx.fromMagnitudeDirection(turretToTargetVectorRTF.magnitude(), angleOffsetTargetToTurret);
        limelightCenterToTargetVector.subtract(VECTOR_LIMELIGHT_TURRET_CENTER);

        airDistanceTurretToTarget = limelightCenterToTargetVector.magnitude();
        double targetHeightDifference =  TARGET_HEIGHT - LIMELIGHT_HEIGHT_TO_FLOOR + HEIGHT_OFFSET_INNER_OUTER_CENTER;

        verticalAngleOffsetLimelightToTarget = Math.toDegrees(Math.atan2(targetHeightDifference, airDistanceTurretToTarget));

        Vector2dEx turretToRobotCenterVector = Vector2dEx.fromMagnitudeDirection(ROBOT_CENTER_TURRET_DISTANCE, gyroYaw);
        robotToTargetVector = turretToTargetVectorRTF.clone();
        robotToTargetVector.subtract(turretToRobotCenterVector);
    }

    public double getVerticalAngleOffsetLimelightToTarget() {
        return verticalAngleOffsetLimelightToTarget;
    }

    public double getTargetToFieldHorizontalAngleOffset() {
        return TargetToFieldHorizontalAngleOffset;
    }

    public double getAirDistanceTurretToTarget() {
        return airDistanceTurretToTarget;
    }

    public double getAngleOffsetTargetToTurret() {
        return angleOffsetTargetToTurret;
    }

    public Vector2dEx getTurretToTargetVectorRTF() {
        return turretToTargetVectorRTF;
    }

    public Vector2dEx getRobotToTargetVector() {
        return robotToTargetVector;
    }

    public OuterTarget getOuterTarget() {
        return outerTarget;
    }
}



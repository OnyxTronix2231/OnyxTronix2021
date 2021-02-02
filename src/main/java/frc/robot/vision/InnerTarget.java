package frc.robot.vision;

import static frc.robot.vision.VisionConstants.*;

public class InnerTarget extends VisionTarget {

    public InnerTarget(double gyroYaw, OuterTarget outerTarget) {

        /*init*/
        super();
        update(gyroYaw, outerTarget);
    }

    public void update(double gyroYawAngle, OuterTarget outerTarget) {

        /*calculating the vector that connects the turret center to the inner target
         * we are connecting the vector from the turret center to the outer target
         * to the fixed vector from the outer target to the inner one*/
        RTFVectorTurretToTarget = outerTarget.getRTFVectorTurretToTarget();
        /*this is a vector addition and NOT a numeric addition of the vector values*/
        RTFVectorTurretToTarget.add(VECTOR_OUTER_INNER_TARGET);

        /*getting the angle between the turret to target vector to the field (gyro 0)
         * the vector is constructed from two vectors on the field coordinates system so
         * it must also be on it*/
        horizontalAngleTargetToRobot = RTFVectorTurretToTarget.direction();

        /*between the vectors from the turret center to the two targets there is a small angle difference
         * we need to calculate it so we could get the inner target vector on the turret coordinate system
         * it is only for calculation purposes, and it might be a negative value*/
        double angleDifferenceOuterToInner = horizontalAngleTargetToRobot -
                outerTarget.getHorizontalAngleTargetToRobot();

        /*the sum of the two angles (the offset from turret to the outer target and the difference
         * between the outer and inner targets) yields the offset between the turret direction to the target
         * this is a very important parameter, this is the value the turret needs to turn to look at the target*/
        horizontalAngleTargetToTurret = outerTarget.getHorizontalAngleTargetToTurret() + angleDifferenceOuterToInner;

        /*we want the air distance between the limelight to the target to calculate the vertical offset
         * so we need to calculate the vector from the limelight to the target. we create a new vector
         * from turret center to the target (this time on the turret coordinate system) and subtract from it
         * the fixed vector from the limelight to the turret center*/
        Vector2dEx limelightCenterToTargetVector =
                Vector2dEx.fromMagnitudeDirection(RTFVectorTurretToTarget.magnitude(), horizontalAngleTargetToTurret);
        /*this is a vector subtraction and NOT a numeric subtraction of the vector's values*/
        limelightCenterToTargetVector.subtract(VECTOR_LIMELIGHT_TURRET_CENTER);

        /*using the limelight to target vector we calculate the horizontal air distance to it*/
        airDistanceTurretToTarget = limelightCenterToTargetVector.magnitude();

        /*using simple trigonometry equation we calculate the vertical angle*/
        verticalAngleLimelightToTarget =
                Math.toDegrees(Math.atan2(TARGET_HEIGHT_DIFFERENCE, airDistanceTurretToTarget));

        /*we create a vector that connects the turret center to the robot it has a fixed magnitude (size)
         and the direction of the robot relative to the field (we get it from the driveTrain subsystem, gyro)*/
        Vector2dEx turretToRobotCenterVector =
                Vector2dEx.fromMagnitudeDirection(ROBOT_CENTER_TURRET_DISTANCE, gyroYawAngle);

        /*to get the vector from the robot center to the target we do some more vector math
         * we subtract the turret to robot vector from the turret to target one and that
         * yields the robot to target vector
         *
         * we clone the turret to target vector so when we make changes on the robot to target vector
         * it won't change the turret to target vector */
        RTFVectorRobotToTarget = RTFVectorTurretToTarget.clone();
        RTFVectorRobotToTarget.subtract(turretToRobotCenterVector);
    }

    @Override
    public double getVerticalAngleLimelightToTarget() {
        return verticalAngleLimelightToTarget;
    }

    @Override
    public double getHorizontalAngleTargetToRobot() {
        return horizontalAngleTargetToRobot;
    }

    @Override
    public double getAirDistanceTurretToTarget() {
        return airDistanceTurretToTarget;
    }

    @Override
    public double getHorizontalAngleTargetToTurret() {
        return horizontalAngleTargetToTurret;
    }

    @Override
    public Vector2dEx getRTFVectorTurretToTarget() {
        return RTFVectorTurretToTarget;
    }

    @Override
    public Vector2dEx getRTFVectorRobotToTarget() {
        return RTFVectorRobotToTarget;
    }
}

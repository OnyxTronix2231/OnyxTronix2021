package frc.robot.vision;

import vision.limelight.target.LimelightTarget;

import static frc.robot.vision.VisionConstants.*;

public class OuterTarget {

    /*a fixed mechanical value- the angle to the floor that the limelight is mounted in*/
    private double limelightAngle;

    /*a fixed mechanical value- the height from the floor that the limelight is mounted in*/
    private double limelightHeight;

    /*a value given by the limelight itself- the horizontal angle offset from the target to the crosshair*/
    private double horizontalAngleOffset;

    /*a value given by the limelight itself- the vertical angle offset from the target to the crosshair*/
    private double verticalAngleOffset;

    /*a calculated value- the horizontal angle offset between the robot direction to the found target*/
    private double robotToTargetHorizontalAngleOffset;

    /*a calculated value- the horizontal air distance from the turret center to the target*/
    private double airDistanceTurretToTarget;

    /*a calculated vector- the vector that is connected from the turret center to the target*/
    private Vector2dEx turretToTargetVector;

    /*a calculated vector- the vector that is connected from the robot center to the target*/
    private Vector2dEx robotToTargetVector;

    public OuterTarget(double gyroYawAngle, double turretAngleRelativeToTarget, LimelightTarget target){
        this.limelightAngle = LIMELIGHT_ANGLE_TO_HORIZON;
        this.limelightHeight = LIMELIGHT_HEIGHT_TO_FLOOR;
        this.update(gyroYawAngle, turretAngleRelativeToTarget, target);
    }

    public void update(double robotGyroYawAngleRTF, double turretAngleRTR, LimelightTarget target) {
        /*if a target is found by the limelight:*/
        if(target != null) {
            /*finding the offsets from limelight crosshair*/
            verticalAngleOffset = target.getVerticalOffsetToCrosshair();
            horizontalAngleOffset = target.getHorizontalOffsetToCrosshair();

            /*calculating the vertical angle from the (target to limelight) to the floor
             *the limelight itself has angle offset from the floor so we sum his default angle
             *with the target offset to crosshair that we get from the limelight itself*/
            double trueVerticalAngleOffset = verticalAngleOffset + limelightAngle;

            /*calculating the vertical height difference from the target to the limelight
             *the limelight itself has some height from the floor so we subtract it
             * from the target full height to get the height difference*/
            double trueHeight = TARGET_HEIGHT - limelightHeight;

            /*calculating air distance (horizontal) from limelight to target using simple formula and trigonometry*/
            double airDistanceLimelightToTarget = trueHeight / Math.tan(Math.toRadians(trueVerticalAngleOffset));

            /*we want to have the vector from the shooting spot on the turret to the target
             * so we create a vector from the limelight to the target and then we add a fixed vector
             * (from the limelight to the turret center) so we have the wanted vector
             * this vector is just for calculations it is in a wrong coordinates system (related to the turret)
             * soon we will define the right one*/
            Vector2dEx tempTurretToTargetVector = Vector2dEx.fromMagnitudeDirection(airDistanceLimelightToTarget, horizontalAngleOffset);
            /*this is a vector addition and NOT a numeric addition of the vector values*/
            tempTurretToTargetVector.add(VECTOR_LIMELIGHT_TURRET_CENTER);

            /*after calculating the correct vector we can calculate its distance to have the true distance
             * from the turret to the target*/
            airDistanceTurretToTarget = tempTurretToTargetVector.magnitude();

            /*as a result from creating the vector we got a new angle, from the turret center to the target*/
            double horizontalAngleOffsetTurretToTarget = tempTurretToTargetVector.direction();

            /*summing the two horizontal angles (from the turret center to target and from the robot to the turret)
             * the angle offset from the robot to the turret is given from the turret subsystem we are just making
             * sure it is a reasonable value from 0 to 360*/
            robotToTargetHorizontalAngleOffset = horizontalAngleOffsetTurretToTarget + turretAngleRTR % 360;

            /*this is the right vector from the turret center to the target it is stated on the correct
            * coordinates system (which is related to the robot and not to the turret) physically it
            * has the same values as the first one
            *
            * we create it with the distance from the turret center to target and a angle offset from the
            * target to the robot direction*/
            turretToTargetVector = Vector2dEx.fromMagnitudeDirection(airDistanceTurretToTarget, robotToTargetHorizontalAngleOffset);

            /*we create a vector that connects the turret center to the robot it has a fixed magnitude (size)
             and direction (relative to the robot)*/
            Vector2dEx turretToRobotCenterVector = Vector2dEx.fromMagnitudeDirection(ROBOT_CENTER_TURRET_DISTANCE, robotGyroYawAngleRTF);

            /*to get the vector from the robot center to the target we do some more vector math
            * we subtract the turret to robot vector from the turret to target one and that
            * yields the robot to target vector
            *
            * we clone the turret to target vector so when we make changes on the robot to target vector
            * it won't change the turret to target vector */
            robotToTargetVector = turretToTargetVector.clone();
            robotToTargetVector.subtract(turretToRobotCenterVector);
        }

        /*and if a target isn't found by the limelight:*/
        else {
            /*putting default values to all parameters*/
            horizontalAngleOffset = 0.0;



        }
    }
}






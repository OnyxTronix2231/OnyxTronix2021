package frc.robot.vision;

import vision.limelight.target.LimelightTarget;

import static frc.robot.vision.VisionConstants.*;

public class OuterTarget {

    /*a fixed mechanical value- the angle to the floor that the limelight is mounted in*/
    private final double limelightAngle;

    /*a fixed mechanical value- the height from the floor that the limelight is mounted in*/
    private final double limelightHeight;

    /*a value given by the limelight itself- the horizontal angle offset from the target to the crosshair*/
    private double horizontalAngleOffsetLimelightToTarget;

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

    public OuterTarget(double gyroYawAngle, double turretAngleRelativeToTarget, LimelightTarget target){
        this.limelightAngle = LIMELIGHT_ANGLE_TO_HORIZON;
        this.limelightHeight = LIMELIGHT_HEIGHT_TO_FLOOR;
        this.update(gyroYawAngle, turretAngleRelativeToTarget, target);
    }

    public void update(double robotGyroYawAngleRTF, double turretAngleRTF, LimelightTarget target) {
        /*if a target is found by the limelight:*/
        if(target != null) {
            /*finding the target offsets from the limelight crosshair*/
            verticalAngleOffsetLimelightToTarget = target.getVerticalOffsetToCrosshair();
            horizontalAngleOffsetLimelightToTarget = target.getHorizontalOffsetToCrosshair();

            /*calculating the vertical angle from the (target to limelight) to the floor
             *the limelight itself has angle offset from the floor so we sum his default angle
             *with the target offset to crosshair that we get from the limelight itself*/
            double verticalAngleOffsetFloorToTarget = verticalAngleOffsetLimelightToTarget + limelightAngle;

            /*calculating the vertical height difference from the target to the limelight
             *the limelight itself has some height from the floor so we subtract it
             * from the target full height to get the height difference*/
            double targetToLimelightHeight = TARGET_HEIGHT - limelightHeight;

            /*calculating air distance (horizontal) from limelight to target using simple formula and trigonometry*/
            double airDistanceLimelightToTarget =
                    targetToLimelightHeight / Math.tan(Math.toRadians(verticalAngleOffsetFloorToTarget));

            /*we want to have the vector from the shooting spot on the turret to the target
             * so we create a vector from the limelight to the target and then we add a fixed vector
             * (from the limelight to the turret center) so we have the wanted vector
             * this vector is just for calculations it is in a wrong coordinates system (related to the turret)
             * soon we will define the correct one*/
            Vector2dEx airTurretToTargetVector = Vector2dEx.fromMagnitudeDirection(airDistanceLimelightToTarget,
                    horizontalAngleOffsetLimelightToTarget);
            /*this is a vector addition and NOT a numeric addition of the vector values*/
            airTurretToTargetVector.add(VECTOR_LIMELIGHT_TURRET_CENTER);

            /*after calculating the correct vector we can calculate its distance to have the true distance
             * from the turret to the target*/
            airDistanceTurretToTarget = airTurretToTargetVector.magnitude();

            /*as a result from creating the vector we got a new angle, from the turret center to the target
            * this is a very important parameter, this is the value the turret needs to turn to look at the target*/
            angleOffsetTargetToTurret = airTurretToTargetVector.direction();

            /*summing the two horizontal angles
             * (from the TargetToFieldHorizontalAngleOffset center to target and from the turret to field)
             *
             * the angle offset from the field (the starting angle of the robot, with gyro) to the turret is given
             * from the turret subsystem we are just making sure it is a reasonable value from 0 to 360*/
            TargetToFieldHorizontalAngleOffset = angleOffsetTargetToTurret + turretAngleRTF % 360;

            /*this is the correct vector (yay!) from the turret center to the target it is stated on the correct
            * coordinates system (which is related to the field and not to the turret) physically it
            * has the same magnitude (size) as the first one
            *
            * we create it with the distance from the turret center to target and an angle offset from the
            * target to the field (the starting angle of the robot, with gyro)*/
            turretToTargetVectorRTF = Vector2dEx.fromMagnitudeDirection
                    (airDistanceTurretToTarget, TargetToFieldHorizontalAngleOffset);

            /*we create a vector that connects the turret center to the robot it has a fixed magnitude (size)
             and the direction of the robot relative to the field (we get it from the driveTrain subsystem, gyro)*/
            Vector2dEx turretToRobotCenterVector =
                    Vector2dEx.fromMagnitudeDirection(ROBOT_CENTER_TURRET_DISTANCE, robotGyroYawAngleRTF);

            /*to get the vector from the robot center to the target we do some more vector math
            * we subtract the turret to robot vector from the turret to target one and that
            * yields the robot to target vector
            *
            * we clone the turret to target vector so when we make changes on the robot to target vector
            * it won't change the turret to target vector */
            robotToTargetVector = turretToTargetVectorRTF.clone();
            robotToTargetVector.subtract(turretToRobotCenterVector);
        }

        /*and if a target isn't found by the limelight:*/
        else {
            /*putting default values to all parameters*/
            horizontalAngleOffsetLimelightToTarget = 0.0;
            verticalAngleOffsetLimelightToTarget = 0.0;
            TargetToFieldHorizontalAngleOffset = 0.0;
            airDistanceTurretToTarget = 0.0;
            turretToTargetVectorRTF = new Vector2dEx(0, 0);
            robotToTargetVector = new Vector2dEx(0, 0);
        }

    }

    public double getLimelightAngle() {
        return limelightAngle;
    }

    public double getLimelightHeight() {
        return limelightHeight;
    }

    public double getHorizontalAngleOffsetLimelightToTarget() {
        return horizontalAngleOffsetLimelightToTarget;
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

}






package frc.robot.vision.visionMainChallenge;

import frc.robot.vision.Vector2dEx;
import vision.limelight.Limelight;
import vision.limelight.target.LimelightTarget;
import java.util.function.DoubleSupplier;
import static frc.robot.vision.VisionConstants.*;

public class OuterTarget extends VisionTarget {

    public OuterTarget(Limelight limelight, DoubleSupplier turretAngleRTF, DoubleSupplier gyroYawAngle) {
        this.limelight = limelight;
        this.turretAngleRTF = turretAngleRTF;
        this.gyroYawAngle = gyroYawAngle;

        /* putting default values to all parameters*/
        verticalAngleLimelightToTarget = 0.0;
        horizontalAngleTargetToRobot = 0.0;
        airDistanceTurretToTarget = 0.0;
        vectorTurretToTargetRTF = new Vector2dEx(0, 0);
        vectorRobotToTargetRTF = new Vector2dEx(0, 0);
    }

    public void update() {
        LimelightTarget target = limelight.getTarget();

        /* if a target is found by the limelight:*/
        if (target != null) {
            /* finding the target angles from the limelight crosshair*/
            verticalAngleLimelightToTarget = target.getVerticalOffsetToCrosshair();
            double horizontalAngleLimelightToTarget = target.getHorizontalOffsetToCrosshair();

            /* calculating the vertical angle from the (target to limelight) to the floor
             * the limelight itself has an angle from the floor so we sum his default angle
             * with the target angle to crosshair that we get from the limelight itself*/
            double verticalAngleRobotToTarget = verticalAngleLimelightToTarget + LIMELIGHT_ANGLE_TO_HORIZON_DEG;

            /* calculating the vertical height difference from the target to the limelight
             * the limelight itself has some height from the floor so we subtract it
             * from the target full height to get the height difference*/
            double targetToLimelightHeight = OUTER_TARGET_HEIGHT_CM - LIMELIGHT_HEIGHT_TO_FLOOR_CM;

            /* calculating air distance (horizontal) from limelight to target using simple formula and trigonometry*/
            double airDistanceLimelightToTarget =
                    targetToLimelightHeight / Math.tan(Math.toRadians(verticalAngleRobotToTarget));

            /* we want to have the vector from the shooting spot on the turret to the target
             * so we create a vector from the limelight to the target and then we add a fixed vector
             * (from the limelight to the turret center) so we have the wanted vector
             * this vector is just for calculations it is in a wrong coordinates system (related to the turret)
             * soon we will define the correct one*/
            Vector2dEx turretToTargetVector = Vector2dEx.fromMagnitudeDirection(airDistanceLimelightToTarget,
                    horizontalAngleLimelightToTarget);

            /* this is a vector addition and NOT a numeric addition of the vector values*/
            turretToTargetVector.add(VECTOR_LIMELIGHT_TO_TURRET);

            /* after calculating the correct vector we can calculate its distance to have the true distance
             * from the turret to the target*/
            airDistanceTurretToTarget = turretToTargetVector.magnitude();

            /* as a result from creating the vector we got a new angle, from the turret center to the target
             * this is a very important parameter, this is the value the turret needs to turn to look at the target*/
            horizontalAngleTargetToTurret = turretToTargetVector.direction();

            /* summing the two horizontal angles
             * (from the horizontalAngleTargetToRobot center to target and from the turret to field)
             *
             * the angle from the field (the starting angle of the robot, with gyro) to the turret is given
             * from the turret subsystem we are just making sure it is a reasonable value from 0 to 360*/
            horizontalAngleTargetToRobot = horizontalAngleTargetToTurret +
                    turretAngleRTF.getAsDouble() % CIRCLE_ANGLES;

            /* this is the correct vector (yay!) from the turret center to the target it is stated on the correct
             * coordinates system (which is related to the field and not to the turret) physically it
             * has the same magnitude (size) as the first one
             *
             * we create it with the distance from the turret center to target and an angle from the
             * target to the field (the starting angle of the robot, with gyro)*/
            vectorTurretToTargetRTF = Vector2dEx.fromMagnitudeDirection
                    (airDistanceTurretToTarget, horizontalAngleTargetToRobot);

            /* we create a vector that connects the turret center to the robot it has a fixed magnitude (size)
             * and the direction of the robot relative to the field (we get it from the driveTrain subsystem, gyro)*/
            Vector2dEx turretToRobotCenterVector =
                    Vector2dEx.fromMagnitudeDirection(ROBOT_TO_TURRET_DISTANCE_CM, gyroYawAngle.getAsDouble());

            /* to get the vector from the robot center to the target we do some more vector math
             * we subtract the turret to robot vector from the turret to target one and that
             * yields the robot to target vector
             *
             * we clone the turret to target vector so when we make changes on the robot to target vector
             * it won't change the turret to target vector */
            vectorRobotToTargetRTF = vectorTurretToTargetRTF.clone();
            vectorRobotToTargetRTF.subtract(turretToRobotCenterVector);
        }

        /* and if a target isn't found by the limelight:*/
        else {
            /* putting default values to all parameters*/
            verticalAngleLimelightToTarget = 0.0;
            horizontalAngleTargetToRobot = 0.0;
            airDistanceTurretToTarget = 0.0;
            vectorTurretToTargetRTF = new Vector2dEx(0, 0);
            vectorRobotToTargetRTF = new Vector2dEx(0, 0);
        }
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
    public Vector2dEx getVectorTurretToTargetRTF() {
        return vectorTurretToTargetRTF;
    }

    @Override
    public Vector2dEx getVectorRobotToTargetRTF() {
        return vectorRobotToTargetRTF;
    }
}

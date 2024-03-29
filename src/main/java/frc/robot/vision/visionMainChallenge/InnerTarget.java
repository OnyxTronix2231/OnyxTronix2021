package frc.robot.vision.visionMainChallenge;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.vision.Vector2dEx;
import vision.limelight.Limelight;
import vision.limelight.target.LimelightTarget;
import java.util.function.DoubleSupplier;
import static frc.robot.vision.visionMainChallenge.MainVisionConstants.*;

public class InnerTarget extends VisionTarget {

    private final OuterTarget outerTarget;

    public InnerTarget(OuterTarget outerTarget, Limelight limelight, DoubleSupplier turretAngleRTF,
                       DoubleSupplier gyroYawAngle) {
        this.outerTarget = outerTarget;
        this.limelight = limelight;
        this.turretAngleRTF = turretAngleRTF;
        this.gyroYawAngle = gyroYawAngle;

        /* putting default values to all parameters*/
        verticalAngleLimelightToTarget = 0.0;
        horizontalAngleTargetToRobot = 0.0;
        airDistanceTurretToTarget = 0.0;
        vectorTurretToTargetRTF = new Vector2dEx(0, 0);
        vectorRobotToTargetRTF = new Vector2dEx(0, 0);
//        Shuffleboard.getTab("Vision").addNumber("Error angle inner", this::getHorizontalAngleTargetToTurret);
    }

    @Override
    public void update() {
        LimelightTarget target = limelight.getTarget();

        if (target != null) {
            turretToTargetVector = outerTarget.getTurretToTargetVector();
            turretToTargetVector.add(VECTOR_OUTER_INNER_TARGET);

            /* calculating the vector that connects the turret center to the inner target
             * we are connecting the vector from the turret center to the outer target
             * to the fixed vector from the outer target to the inner one*/
            vectorTurretToTargetRTF = outerTarget.getVectorTurretToTargetRTF();

            /* this is a vector addition and NOT a numeric addition of the vector values*/
            vectorTurretToTargetRTF.add(VECTOR_OUTER_INNER_TARGET);

            /* getting the angle between the turret to target vector to the field (gyro 0)
             * the vector is constructed from two vectors on the field coordinates system so
             * it must also be on it*/
            horizontalAngleTargetToRobot = vectorTurretToTargetRTF.direction();

            /* using the limelight to target vector we calculate the horizontal air distance to it*/
            airDistanceTurretToTarget = vectorTurretToTargetRTF.magnitude();

            /* between the vectors from the turret center to the two targets there is a small angle difference
             * we need to calculate it so we could get the inner target vector on the turret coordinate system
             * it is only for calculation purposes, and it might be a negative value*/
            double angleOuterToInner = horizontalAngleTargetToRobot -
                    outerTarget.getHorizontalAngleTargetToRobot();

            /* the sum of the two angles (the offset from turret to the outer target and the difference
             * between the outer and inner targets) yields the offset between the turret direction to the target
             * this is a very important parameter, this is the value the turret needs to turn to look at the target*/
            horizontalAngleTargetToTurret = outerTarget.getHorizontalAngleTargetToTurret() + angleOuterToInner;

            /* we want the air distance between the limelight to the target to calculate the vertical offset
             * so we need to calculate the vector from the limelight to the target. we create a new vector
             * from turret center to the target (this time on the turret coordinate system) and subtract from it
             * the fixed vector from the limelight to the turret center*/
            Vector2dEx limelightToTargetVector =
                    Vector2dEx.fromMagnitudeDirection(vectorTurretToTargetRTF.magnitude(),
                            horizontalAngleTargetToTurret);

            /* this is a vector subtraction and NOT a numeric subtraction of the vector's values*/
            limelightToTargetVector.subtract(VECTOR_TURRET_TO_LIMELIGHT);

            /* using the limelight to target vector we calculate the horizontal air distance to it*/
            double airDistanceLimelightToInnerTarget = limelightToTargetVector.magnitude();

            /* using simple trigonometry equation we calculate the vertical angle*/
            verticalAngleLimelightToTarget =
                    Math.toDegrees(Math.atan2(INNER_TARGET_HEIGHT_CM - LIMELIGHT_HEIGHT_TO_FLOOR_CM,
                            airDistanceLimelightToInnerTarget));

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
        } else {
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
        return turretToTargetVector == null ? 0 : turretToTargetVector.direction() % 360;
    }

    @Override
    public Vector2dEx getVectorTurretToTargetRTF() {
        return vectorTurretToTargetRTF;
    }

    @Override
    public Vector2dEx getVectorRobotToTargetRTF() {
        return vectorRobotToTargetRTF;
    }

    public Vector2dEx getTurretToTargetVector(){
        return turretToTargetVector;
    }
}

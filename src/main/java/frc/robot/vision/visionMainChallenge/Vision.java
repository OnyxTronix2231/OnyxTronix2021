package frc.robot.vision.visionMainChallenge;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.vision.BaseVision;
import frc.robot.vision.Vector2dEx;

import java.util.function.DoubleSupplier;

import static frc.robot.vision.visionMainChallenge.MainVisionConstants.*;

public class Vision extends BaseVision {

    private final OuterTarget outerTarget;
    private final InnerTarget innerTarget;
    private final DoubleSupplier gyroYawAngle;
    private VisionTarget chosenTarget;
    private Pose2d currentPos;
    private Rotation2d currentRotation;
    private DoubleSupplier turretAngleRTF;

    public Vision(DoubleSupplier gyroYawAngle, DoubleSupplier turretAngleRTF) {
        this.gyroYawAngle = gyroYawAngle;
        this.turretAngleRTF = turretAngleRTF;
        currentRotation = new Rotation2d(Math.toRadians(gyroYawAngle.getAsDouble()));
        currentPos = new Pose2d(0, 0, currentRotation);
        outerTarget = new OuterTarget(limelight, turretAngleRTF, gyroYawAngle);
        innerTarget = new InnerTarget(outerTarget, limelight, turretAngleRTF, gyroYawAngle);
        Shuffleboard.getTab("Vision").addNumber("turret angle", this.turretAngleRTF);
        Shuffleboard.getTab("Vision").addNumber("gyro", gyroYawAngle);
        /* Shuffleboard.getTab("Vision").addNumber("Distance to outer target",
                outerTarget::getAirDistanceTurretToTarget);
        Shuffleboard.getTab("Vision").addNumber("Distance to inner target",
                innerTarget::getAirDistanceTurretToTarget);
        Shuffleboard.getTab("Vision").addString("Chosen target",
                () -> chosenTarget == outerTarget ? "outer" : "inner" );
        Shuffleboard.getTab("Vision").addNumber("Angle turret to chosen target",
                () -> chosenTarget != null ? chosenTarget.getHorizontalAngleTargetToTurret() : -1);
        Shuffleboard.getTab("Vision").addNumber("Angle robot to chosen target",
                () -> chosenTarget != null ? chosenTarget.getHorizontalAngleTargetToRobot() : -1);
        Shuffleboard.getTab("Vision").addNumber("Angle limelight to target vertical",
                () -> chosenTarget != null ? chosenTarget.getVerticalAngleLimelightToTarget() : -1);
        Shuffleboard.getTab("Vision").addNumber("Calculated position X", () -> currentPos.getX());
        Shuffleboard.getTab("Vision").addNumber("Calculated position Y", () -> currentPos.getY());
        Shuffleboard.getTab("Vision").addNumber("Calculated rotation", () -> currentRotation.getDegrees());*/
    }

    @Override
    public void update() {
        outerTarget.update();
        innerTarget.update();
        chooseTarget();
        updatePos();
    }

    public void chooseTarget() {
        if (hasTarget()) {
            boolean innerTargetCondition = outerTarget.getAirDistanceTurretToTarget() < MAX_AIR_DISTANCE_OUTER_CM &&
                    outerTarget.getAirDistanceTurretToTarget() > MIN_AIR_DISTANCE_OUTER_CM &&
                    Math.abs(outerTarget.getHorizontalAngleTargetToRobot()) < MAX_ABS_ANGLE_TARGET_TO_FIELD_DEG;
            if (innerTargetCondition) {
                chosenTarget = innerTarget;
            } else {
                chosenTarget = outerTarget;
            }
        } else {
            chosenTarget = null;
        }
    }

    public void updatePos() {
        if (hasTarget()) {
            /* we create a vector from the robot to the zero point of the field by taking the vector
             * from the robot to the target and subtracting from it the fixed vector from the zero point
             * to the outer target*/
            Vector2dEx robotToFieldVector = outerTarget.getVectorRobotToTargetRTF();

            /* this is a vector subtraction and NOT a numeric subtraction of the vector's values*/
            robotToFieldVector.subtract(VECTOR_FIELD_ZERO_TO_OUTER);

            /* the pos x and y are given from the vector itself in centimeters*/
            double y = robotToFieldVector.y / CM_IN_METER;
            double x = robotToFieldVector.x / CM_IN_METER;

            /* updating the position and rotation bt creating new instances of Rotation2d and Position2d with the
             * calculated values*/
            currentRotation = new Rotation2d(Math.toRadians(gyroYawAngle.getAsDouble()));
            currentPos = new Pose2d(x, y, currentRotation);
        }
    }

    public double DistanceToTargetWall() {
        return outerTarget.getAirDistanceTurretToTarget() *
                Math.cos(Math.toRadians(outerTarget.getHorizontalAngleTargetToRobot()));
    }

    public double getRobotX() {
        return currentPos.getX();
    }

    public double getRobotY() {
        return currentPos.getY();
    }

    public Pose2d getCurrentPos() {
        return currentPos;
    }

    public Rotation2d getCurrentRotation() {
        return currentRotation;
    }

    public VisionTarget getChosenTarget() {
        return chosenTarget;
    }

    public OuterTarget getOuterTarget() {
        return outerTarget;
    }
}

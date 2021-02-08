package frc.robot.vision.visionMainChallenge;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.vision.Vector2dEx;
import vision.limelight.Limelight;
import java.util.function.DoubleSupplier;
import static frc.robot.vision.VisionConstants.*;

public class Vision {

    private final Limelight limelight;
    private final OuterTarget outerTarget;
    private final InnerTarget innerTarget;
    private final DoubleSupplier gyroYawAngle;
    private VisionTarget chosenTarget;
    private Pose2d currentPos;
    private Rotation2d currentRotation;

    public Vision(DoubleSupplier gyroYawAngle, DoubleSupplier turretAngleRTF) {
        this.gyroYawAngle = gyroYawAngle;
        currentRotation = new Rotation2d(Math.toRadians(gyroYawAngle.getAsDouble()));
        currentPos = new Pose2d(0, 0, currentRotation);
        limelight = Limelight.getInstance();
        outerTarget = new OuterTarget(limelight, turretAngleRTF, gyroYawAngle);
        innerTarget = new InnerTarget(outerTarget, limelight, turretAngleRTF, gyroYawAngle);
        Shuffleboard.getTab("Vision").addNumber("Distance to chosen target", () -> chosenTarget.getAirDistanceTurretToTarget());
        Shuffleboard.getTab("Vision").addNumber("Angle turret to chosen target", () -> chosenTarget.getHorizontalAngleTargetToTurret());
        Shuffleboard.getTab("Vision").addNumber("Angle robot to chosen target", () -> chosenTarget.getHorizontalAngleTargetToRobot());
        Shuffleboard.getTab("Vision").addNumber("Calculated position X", () -> currentPos.getX());
        Shuffleboard.getTab("Vision").addNumber("Calculated position Y", () -> currentPos.getY());
        Shuffleboard.getTab("Vision").addNumber("Calculated rotation", () -> currentRotation.getDegrees());
    }

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
                    Math.abs(outerTarget.getHorizontalAngleTargetToRobot()) <
                            MAX_ABS_ANGLE_TARGET_TO_FIELD_DEG;
            if (innerTargetCondition) {
                chosenTarget = innerTarget;
            } else {
                chosenTarget = outerTarget;
            }
        } else {
            chosenTarget = null;
        }
    }

    public void updatePos(){
        if (hasTarget()) {
            /* we create a vector from the robot to the zero point of the field by taking the vector
             * from the robot to the target and subtracting from it the fixed vector from the zero point
             * to the outer target*/
            Vector2dEx robotToFieldVector = outerTarget.getVectorRobotToTargetRTF();

            /* this is a vector subtraction and NOT a numeric subtraction of the vector's values*/
            robotToFieldVector.subtract(VECTOR_FIELD_ZERO_TO_OUTER);

            /* the pos x and y are given from the vector itself in centimeters*/
            double y = robotToFieldVector.y;
            double x = robotToFieldVector.x;

            /* updating the position and rotation bt creating new instances of Rotation2d and Position2d with the
             * calculated values*/
            currentRotation = new Rotation2d(Math.toRadians(gyroYawAngle.getAsDouble()));
            currentPos = new Pose2d(x, y, currentRotation);
        }
    }

    public double getAbsoluteDistanceToOuterTargetWall() {
        return outerTarget.getAirDistanceTurretToTarget() *
                Math.cos(Math.toRadians(outerTarget.getHorizontalAngleTargetToRobot()));
    }

    public boolean hasTarget() {
        return limelight.targetFound();
    }

    public double getRobotX(){
        return currentPos.getX();
    }

    public double getRobotY(){
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
}

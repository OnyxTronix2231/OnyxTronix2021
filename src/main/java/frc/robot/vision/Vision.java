package frc.robot.vision;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
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
    }

    public void update() {
        outerTarget.update();
        innerTarget.update();
        chooseTarget();
        updatePos();
    }

    public void chooseTarget() {
        if (hasTarget()) {
            boolean innerTargetCondition = outerTarget.getAirDistanceTurretToTarget() < MAX_AIR_DISTANCE_OUTER &&
                    outerTarget.getAirDistanceTurretToTarget() > MIN_AIR_DISTANCE_OUTER &&
                    Math.abs(outerTarget.getHorizontalAngleTargetToRobot()) <
                            MAX_ABS_OFFSET_TARGET_TO_FIELD;
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
            /*we calculated this distance inside of the outer target class we just save it for later use*/
            double distanceTargetToRobot = outerTarget.getAirDistanceTurretToTarget();

            /*the pos x and y are calculated using a simple trigonometric formula, we have the angle to
            * the field (from the gyro) and the distance*/
            double y = TARGET_Y - Math.sin(Math.toRadians(gyroYawAngle.getAsDouble())) * distanceTargetToRobot;
            double x = TARGET_X - Math.cos(Math.toRadians(gyroYawAngle.getAsDouble())) * distanceTargetToRobot;

            /*updating the position and rotation bt creating new instances of Rotation2d and Position2d with the
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

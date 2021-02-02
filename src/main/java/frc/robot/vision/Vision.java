package frc.robot.vision;

import vision.limelight.Limelight;

import java.util.function.DoubleSupplier;

import static frc.robot.vision.VisionConstants.*;

public class Vision {
    private Limelight limelight;
    private OuterTarget outerTarget;
    private InnerTarget innerTarget;
    private DoubleSupplier gyroYawAngle;
    private DoubleSupplier turretAngleRTF;
    private VisionTarget chosenTarget;

    public Vision(DoubleSupplier gyroYawAngle, DoubleSupplier turretAngleRTF) {
        gyroYawAngle = gyroYawAngle;
        turretAngleRTF = turretAngleRTF;
        limelight = Limelight.getInstance();
        outerTarget = new OuterTarget(gyroYawAngle.getAsDouble(),
                turretAngleRTF.getAsDouble(), limelight.getTarget());
        innerTarget = new InnerTarget(gyroYawAngle.getAsDouble(),
                outerTarget);
        chooseTarget();
    }

    public void update() {
        limelight = Limelight.getInstance();
        outerTarget.update(gyroYawAngle.getAsDouble(),
                turretAngleRTF.getAsDouble(),
                limelight.getTarget());
        innerTarget.update(gyroYawAngle.getAsDouble(),
                outerTarget);
        chooseTarget();
    }

    public void chooseTarget() {
        boolean innerTargetCondition = outerTarget.getAirDistanceTurretToTarget() < MAX_AIR_DISTANCE_OUTER &&
                outerTarget.getAirDistanceTurretToTarget() > MIN_AIR_DISTANCE_OUTER &&
                Math.abs(outerTarget.getHorizontalAngleTargetToRobot()) <
                        MAX_ABS_OFFSET_TARGET_TO_FIELD;
        if (innerTargetCondition) {
            chosenTarget = innerTarget;
        } else {
            chosenTarget = outerTarget;
        }
    }

    public double getAbsoluteDistanceToOuterTargetWall() {
        return outerTarget.getAirDistanceTurretToTarget() *
                Math.cos(Math.toRadians(outerTarget.getHorizontalAngleTargetToRobot()));
    }

    public boolean hasTarget() {
        return limelight.targetFound();
    }
}

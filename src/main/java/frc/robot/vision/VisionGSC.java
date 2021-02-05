package frc.robot.vision;

import vision.limelight.Limelight;

import static frc.robot.vision.VisionConstants.*;

public class VisionGSC {

    private final Limelight limelight;

    public VisionGSC() {
        limelight = Limelight.getInstance();
    }

    public boolean hasTarget() {
        return limelight.targetFound();
    }

    public double getTargetAreaPercentage() {
        if (hasTarget()) {
            return limelight.getTarget().getTargetArea();
        }
        return DEFAULT_TARGET_AREA;
    }

    public double getTargetHorizontalAngle() {
        if (hasTarget()) {
            return limelight.getTarget().getHorizontalOffsetToCrosshair();
        }
        return DEFAULT_HORIZONTAL_ANGLE;
    }

    public GSCOption determineBlueOrRed() {
        if (Math.abs(RED_AREA_PERCENTAGE - getTargetAreaPercentage()) >
                Math.abs(BLUE_AREA_PERCENTAGE - getTargetAreaPercentage())) {
            if (Math.abs(getTargetHorizontalAngle()) < TARGET_ANGLE_TOLERANCE) {
                return GSCOption.RED2;
            }
            return GSCOption.RED1;
        }
        if (Math.abs(getTargetHorizontalAngle()) < TARGET_ANGLE_TOLERANCE) {
            return GSCOption.BLUE2;
        }
        return GSCOption.BLUE1;
    }

    public void update() {}
}

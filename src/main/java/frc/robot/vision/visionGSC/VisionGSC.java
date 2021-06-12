package frc.robot.vision.visionGSC;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.vision.BaseVision;

import static frc.robot.vision.visionGSC.VisionGSCConstants.*;

public class VisionGSC extends BaseVision{

    private GSCOption chosenOption;

    public VisionGSC(){
        Shuffleboard.getTab("Vision").addString("Chosen path", () -> chosenOption != null ? chosenOption.toString() : "no path");
        Shuffleboard.getTab("Vision").addNumber("Area precentage", this::getTargetAreaPercentage);
        Shuffleboard.getTab("Vision").addNumber("Horizontal angle", this::getTargetHorizontalAngle);
    }

    public double getTargetAreaPercentage() {
        if (hasTarget()) {
            return limelight.getTarget().getTargetArea();
        }
        return 0;
    }

    public double getTargetHorizontalAngle() {
        if (hasTarget())
            return limelight.getTarget().getHorizontalOffsetToCrosshair();
        return DEFAULT_VALUE_FOR_ANGLE_TO_TARGET;
    }

    public GSCOption determineBlueOrRed() {
        if (!hasTarget()) {
            return GSCOption.NOT_FOUND;
        }

        if (Math.abs(RED_AREA_PERCENTAGE - getTargetAreaPercentage()) <
                Math.abs(BLUE_AREA_PERCENTAGE - getTargetAreaPercentage())) {
            if (Math.abs(getTargetHorizontalAngle()) > RED_TARGET_ANGLE_TOLERANCE) {
                return GSCOption.RED2;
            }
            return GSCOption.RED1;
        }
        if (Math.abs(getTargetHorizontalAngle()) > BLUE_TARGET_ANGLE_TOLERANCE) {
            return GSCOption.BLUE2;
        }
        return GSCOption.BLUE1;
    }

    @Override
    public void update() {
        chosenOption = determineBlueOrRed();
    }

    public GSCOption getChosenOption() {
        return chosenOption;
    }
}

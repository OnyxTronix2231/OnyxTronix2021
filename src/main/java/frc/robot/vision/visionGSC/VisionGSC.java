package frc.robot.vision.visionGSC;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.vision.BaseVision;

import static frc.robot.vision.VisionConstants.VisionGSCConstants.*;

public class VisionGSC extends BaseVision{

    private GSCOption chosenOption;

    public VisionGSC(){
        Shuffleboard.getTab("Vision").addString("Chosen path", () -> chosenOption.toString());
    }

    public double getTargetAreaPercentage() {
        return limelight.getTarget().getTargetArea();
    }

    public double getTargetHorizontalAngle() {
        return limelight.getTarget().getHorizontalOffsetToCrosshair();
    }

    public GSCOption determineBlueOrRed() {
        if (!hasTarget()) {
            return GSCOption.NOT_FOUND;
        }

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

    @Override
    public void update() {
        chosenOption = determineBlueOrRed();
    }

    public GSCOption getChosenOption() {
        return chosenOption;
    }
}

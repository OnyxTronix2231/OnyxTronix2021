package frc.robot.vision;

import vision.limelight.Limelight;

import static frc.robot.vision.VisionConstants.*;

public class VisionGSC {
    private Limelight limelight;

    public VisionGSC(){
        this.limelight = Limelight.getInstance();
    }

    public boolean hasTarget(){
        return this.limelight.targetFound();
    }

    public double getTargetAreaPercentage(){
        if(this.hasTarget()){
            return this.limelight.getTarget().getTargetArea();
        }
        return DEFAULT_TARGET_AREA;
    }

    public GSCOption determineBlueOrRed(){
        if(Math.abs(RED_AREA_PERCENTAGE - getTargetAreaPercentage()) >
                Math.abs(BLUE_AREA_PERCENTAGE - getTargetAreaPercentage())) {
            return GSCOption.Blue;
        }
        return GSCOption.Red;
    }
}

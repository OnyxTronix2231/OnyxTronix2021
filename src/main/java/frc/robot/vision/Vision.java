package frc.robot.vision;

import org.apache.logging.log4j.core.appender.ConsoleAppender;
import vision.limelight.Limelight;
import vision.limelight.target.LimelightTarget;

import static frc.robot.vision.VisionConstants.*;
import static frc.robot.vision.VisionConstants.AREA_TOLARANCE;


public class Vision {
    private final Limelight limelight;

    public Vision(){
        this.limelight = Limelight.getInstance();
    }

    public boolean hasTarget(){
        return this.limelight.targetFound();
    }

    public double getHorizontalAngleToTarget(){
        if(this.hasTarget()){
            return this.limelight.getTarget().getHorizontalOffsetToCrosshair() + HORIZONTAL_OFFSET;
        }
        return DEFAULT_HORIZONTAL_OFFSET;
    }

    public double getVerticalAngleToTarget(){
        if(this.hasTarget()){
            return this.limelight.getTarget().getVerticalOffsetToCrosshair() + VERTICAL_OFFSET;
        }
        return DEFAULT_VERTICAL_OFFSET;
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

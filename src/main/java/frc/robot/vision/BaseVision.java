package frc.robot.vision;

import vision.limelight.Limelight;

public abstract class BaseVision {

    protected Limelight limelight;

    public BaseVision() {
        limelight = Limelight.getInstance();
    }

    public boolean hasTarget() {
        return limelight.targetFound();
    }

    public abstract void update();
}

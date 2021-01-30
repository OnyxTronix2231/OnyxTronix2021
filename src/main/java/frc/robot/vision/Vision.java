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

    public Vision(DoubleSupplier gyroYawAngle, DoubleSupplier turretAngleRTF){
        this.gyroYawAngle = gyroYawAngle;
        this.turretAngleRTF = turretAngleRTF;
        this.limelight = Limelight.getInstance();
        this.outerTarget = new OuterTarget(this.gyroYawAngle.getAsDouble(),
                turretAngleRTF.getAsDouble(),
                this.limelight.getTarget());
        this.innerTarget = new InnerTarget(this.gyroYawAngle.getAsDouble(),
                this.outerTarget);
        this.chooseTarget();
    }

    public void update(){
        this.limelight = Limelight.getInstance();
        this.outerTarget.update(gyroYawAngle.getAsDouble(),
                turretAngleRTF.getAsDouble(),
                this.limelight.getTarget());
        this.innerTarget.update(gyroYawAngle.getAsDouble(),
                this.outerTarget);
        this.chooseTarget();
    }

    public void chooseTarget(){
        boolean condition = outerTarget.getAirDistanceTurretToTarget() < MAX_AIR_DISTANCE_OUTER &&
                            outerTarget.getAirDistanceTurretToTarget() > MIN_AIR_DISTANCE_OUTER &&
                            Math.abs(outerTarget.getHorizontalAngleTargetToRobot()) <
                                    MAX_ABS_OFFSET_TARGET_TO_FIELD;
        if (condition){
            this.chosenTarget = this.innerTarget;
        } else {
            this.chosenTarget = this.outerTarget;
        }
    }

    public double getAbsoluteDistanceToOuterTargetWall(){
        return outerTarget.getAirDistanceTurretToTarget() *
                Math.cos(Math.toRadians(outerTarget.getHorizontalAngleTargetToRobot()));
    }

    public boolean hasTarget(){
        return this.limelight.targetFound();
    }
}

package frc.robot.vision;

import vision.limelight.Limelight;

import java.util.function.DoubleSupplier;


public class Vision {
    private Limelight limelight;
    private OuterTarget outerTarget;
    private InnerTarget innerTarget;
    private DoubleSupplier gyroYawAngle;
    private DoubleSupplier turretAngleRTF;

    public Vision(DoubleSupplier gyroYawAngle, DoubleSupplier turretAngleRTF){
        this.gyroYawAngle = gyroYawAngle;
        this.turretAngleRTF = turretAngleRTF;
        this.limelight = Limelight.getInstance();
        this.outerTarget = new OuterTarget(this.gyroYawAngle.getAsDouble(),
                turretAngleRTF.getAsDouble(),
                this.limelight.getTarget());
        this.innerTarget = new InnerTarget(this.gyroYawAngle.getAsDouble(),
                this.outerTarget);
    }

    public void update(){
        this.limelight = Limelight.getInstance();
        this.outerTarget.update(gyroYawAngle.getAsDouble(),
                turretAngleRTF.getAsDouble(),
                this.limelight.getTarget());
        this.innerTarget.update(gyroYawAngle.getAsDouble(),
                this.outerTarget);
    }

    public double getAbsoluteDistanceToOuterTargetWall(){
        return outerTarget.getAirDistanceTurretToTarget() *
                Math.cos(Math.toRadians(outerTarget.getTargetToFieldHorizontalAngleOffset()));
    }

    public boolean hasTarget(){
        return this.limelight.targetFound();
    }
}

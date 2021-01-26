package frc.robot.vision;

import org.apache.logging.log4j.core.appender.ConsoleAppender;
import vision.limelight.Limelight;
import vision.limelight.target.LimelightTarget;

import java.util.function.DoubleSupplier;

import static frc.robot.vision.VisionConstants.*;
import static frc.robot.vision.VisionConstants.AREA_TOLARANCE;


public class Vision {
    private Limelight limelight;
    private OuterTarget outerTarget;
    private InnerTarget innerTarget;

    public Vision(DoubleSupplier gyroYawAngle, DoubleSupplier turretAngleRelativeToTarget){
        this.limelight = Limelight.getInstance();
        this.outerTarget = new OuterTarget(gyroYawAngle.getAsDouble(),
                turretAngleRelativeToTarget.getAsDouble(),
                this.limelight.getTarget());
        this.innerTarget = new InnerTarget(gyroYawAngle.getAsDouble(),
                this.outerTarget);
    }

    public void update(DoubleSupplier gyroYawAngle, DoubleSupplier turretAngleRelativeToTarget){
        this.limelight = Limelight.getInstance();
        this.outerTarget.update(gyroYawAngle.getAsDouble(),
                turretAngleRelativeToTarget.getAsDouble(),
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

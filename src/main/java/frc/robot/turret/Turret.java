package frc.robot.turret;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.turret.TurretConstants.*;

public class Turret extends SubsystemBase {
    private final TurretComponents components;
    private double startingAngle;
    private double targetAngle;

    public Turret(TurretComponents turretComponents) {
        this.components = turretComponents;
    }

    public void MoveTurretBySpeed(double speed) {
        components.getMasterMotor().set(speed);
    }

    public void stop() {
        components.getTurretController().disable();
    }

    public void initMoveToAngle(double angle) {
        angle = getAngleByLimits(angle);
        components.getTurretController().setSetpoint(angleToEncoderUnits(angle));
        components.getTurretController().enable();
    }

    public void updateMoveToAngle(double angle) {
        angle = getAngleByLimits(angle);
        components.getTurretController().update(angleToEncoderUnits(angle));
    }

    public void initMoveByAngle(double angle) {
        startingAngle = getAngleRTR();
        targetAngle = angle;
        initMoveToAngle(startingAngle + angle);
    }

    public void updateMoveByAngle(double angle) {
        if (targetAngle != angle){
            startingAngle = getAngleRTR();
            targetAngle = angle;
        }
        updateMoveToAngle(startingAngle + angle);
    }

    public double getAngleRTR() {
        return encoderUnitsToAngle(components.getEncoder().getCount());
    }

    public double encoderUnitsToAngle(double encoderUnits) {
        return encoderUnits / (ENCODER_UNITS * RATIO) * DEGREES_IN_CIRCLE;
    }


    public double angleToEncoderUnits(double angle) {
        return ((ENCODER_UNITS * RATIO) / DEGREES_IN_CIRCLE) * angle;
    }

    public double getAngleByLimits(double angle){
        angle = angle % DEGREES_IN_CIRCLE;
        if(angle > MAX_DEGREE){
            angle = (angle - FLIP_POINT);
        } else if (angle < MIN_DEGREE){
            angle = (angle + FLIP_POINT);
        }
        return angle;
    }

    public boolean isOnTarget(){
        return components.getTurretController().isOnTarget(angleToEncoderUnits(TOLERANCE_DEGREE));
    }
}

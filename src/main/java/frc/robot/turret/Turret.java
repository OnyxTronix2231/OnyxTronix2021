package frc.robot.turret;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.turret.TurretConstants.*;

public class Turret extends SubsystemBase {
    private final TurretComponents components;
    private double startingAngle;

    public Turret(TurretComponents turretComponents) {
        this.components = turretComponents;
    }

    public void setSpeed(double speed) {
        components.getMasterMotor().set(speed);
    }

    public void stop() {
        setSpeed(0);
    }

    public void disableController() {
        components.getPIDController().disable();
    }

    public void initMoveToAngle(double angle) {
        components.getPIDController().setSetpoint(angleToEncoderUnits(angle));
        components.getPIDController().enable();
    }

    public void updateMoveToAngle(double angle) {
        components.getPIDController().update(angleToEncoderUnits(angle));
    }


    public void initMoveByAngle(double angle) {
        startingAngle = getAngle();
        components.getPIDController().setSetpoint(angleToEncoderUnits(startingAngle + angle));
        components.getPIDController().enable();
    }

    public void updateMoveByAngle(double angle) {
        components.getPIDController().update(angleToEncoderUnits(angle + startingAngle));
    }

    public double getAngle() {
        return encoderUnitsToAngle(components.getEncoder().getCount());
    }

    public double encoderUnitsToAngle(double encoderUnits) {
        return encoderUnits / (ENCODER_UNITS * RATIO) * DEGREES_IN_CIRCLE;
    }

    public double angleToEncoderUnits(double angle) {
        return ((ENCODER_UNITS * RATIO) / DEGREES_IN_CIRCLE) * angle;
    }
}

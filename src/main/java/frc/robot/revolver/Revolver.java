package frc.robot.revolver;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.revolver.RevolverConstants.ENCODER_UNITS_PER_ROTATION;
import static frc.robot.revolver.RevolverConstants.SECONDS_IN_MIN;
import static frc.robot.revolver.RevolverConstants.HUNDREDS_OF_MILLISECS_IN_SEC;
import static frc.robot.revolver.RevolverConstants.RevolverComponentsA.TOLERANCE;

public class Revolver extends SubsystemBase {

    private final RevolverComponents components;

    public Revolver(RevolverComponents components) {
        this.components = components;
    }

    public void moveRevolverBySpeed(double speed) {
        components.getMasterMotor().set(speed);
    }

    public void moveRevolverByRPM(double RPM) {
        components.getPIDController().setSetpoint(RPMToEncoderUnit(RPM));
        components.getPIDController().enable();
    }

    public void updateRevolverRPM(double RPM) {
        components.getPIDController().update(RPMToEncoderUnit(RPM));
    }

    public void stop() {
        moveRevolverBySpeed(0);
    }

    public boolean isOnTarget() {
        return components.getPIDController().isOnTarget(TOLERANCE);
    }

    public double RPMToEncoderUnit(double RPM) {
        return RPM * ENCODER_UNITS_PER_ROTATION / (SECONDS_IN_MIN * HUNDREDS_OF_MILLISECS_IN_SEC);
    }

    public double encoderUnitsToRPM(double encoderUnits) {
        return (encoderUnits * (SECONDS_IN_MIN * HUNDREDS_OF_MILLISECS_IN_SEC)) /ENCODER_UNITS_PER_ROTATION;
    }
}

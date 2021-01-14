package frc.robot.revolver;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.revolver.RevolverConstants.RevolverComponentsA.ENCODER_UNITS;
import static frc.robot.revolver.RevolverConstants.RevolverComponentsA.TOLERANCE;
import static frc.robot.revolver.RevolverConstants.RevolverComponentsA.SECONDS_IN_MIN;
import static frc.robot.revolver.RevolverConstants.RevolverComponentsA.HUNDREDS_OF_MILLISECS_IN_SEC;

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
        return RPM * ENCODER_UNITS / SECONDS_IN_MIN / HUNDREDS_OF_MILLISECS_IN_SEC;
    }
}

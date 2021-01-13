package frc.robot.revolver;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

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
    }

    public double RPMToEncoderUnit(double RPM) {
        return RPM * 1023 / 60 / 10;
    }

    public void stop() {
        moveRevolverBySpeed(0);
    }
}

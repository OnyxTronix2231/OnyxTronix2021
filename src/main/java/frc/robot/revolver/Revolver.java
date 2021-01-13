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

    public void stop() {
        moveRevolverBySpeed(0);
    }
}

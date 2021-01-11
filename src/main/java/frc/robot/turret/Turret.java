package frc.robot.turret;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import pid.PIDControlMode;

public class Turret extends SubsystemBase {
    private final TurretComponents components;

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

    public void moveToAngle(double angle) {
        components.getPIDController().setSetpoint(angle);
        components.getPIDController().enable(PIDControlMode.Position);
    }
}

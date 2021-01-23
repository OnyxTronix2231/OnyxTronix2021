package frc.robot.shooter;

import static frc.robot.shooter.ShooterConstants.*;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.MAX_VELOCITY;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    private final ShooterComponents components;
    private final NetworkTableEntry KP;
    private final NetworkTableEntry KI;
    private final NetworkTableEntry KD;
    private final NetworkTableEntry KF;
    public Shooter(ShooterComponents components) {
        this.components = components;
        lastRPMError = Integer.MAX_VALUE;
        Shuffleboard.getTab("Shooter").addNumber("PID Error",
                () -> components.getMasterMotor().getClosedLoopError());
        Shuffleboard.getTab("Shooter").addNumber("Current Shooter Motor RPM",
                () -> encoderUnitsInDecisecondToRPM(components.getShooterEncoder().getRate()));

        KP = Shuffleboard.getTab("Shooter").add("KP",
                components.getShooterController().getPIDFTerms().getKp()).getEntry();
        KI = Shuffleboard.getTab("Shooter").add("KI",
                components.getShooterController().getPIDFTerms().getKi()).getEntry();
        KD = Shuffleboard.getTab("Shooter").add("KD",
                components.getShooterController().getPIDFTerms().getKd()).getEntry();
        KF = Shuffleboard.getTab("Shooter").add("KF",
                components.getShooterController().getPIDFTerms().getKf()).getEntry();
    }

    @Override
    public void periodic() {
        components.getShooterController().setPIDFTerms(
                KP.getDouble(components.getShooterController().getPIDFTerms().getKp()),
                KI.getDouble(components.getShooterController().getPIDFTerms().getKi()),
                KD.getDouble(components.getShooterController().getPIDFTerms().getKd()),
                KF.getDouble(components.getShooterController().getPIDFTerms().getKf()));
    }

    public void moveShooterBySpeed(double speed) {
        components.getMasterMotor().set(speed);
    }

    public void stopShooter() {
        components.getShooterController().disable();
    }

    public void initMoveShooterByRPM(double RPM) {
        components.getShooterController().setSetpoint(RPMToEncoderUnitsInDecisecond(RPM));
        components.getShooterController().enable();
    }

    public void updateMoveShooterByRPM(double RPM) {
        components.getShooterController().update(RPMToEncoderUnitsInDecisecond(RPM));
    }

    public double distanceMeterToEncoderUnitInDecisecond(double distance) { //TODO Change and add angle
        double encoderUnitsTarget;
        if (distance > MIDDLE_DISTANCE) {
            encoderUnitsTarget = ShooterConstants.ShooterCalculation.FORMULA_DISTANCE_FAR(distance);
        } else {
            encoderUnitsTarget = ShooterConstants.ShooterCalculation.FORMULA_DISTANCE_CLOSE(distance);
        }
        return Math.min(encoderUnitsTarget, MAX_VELOCITY);
    }

    public double RPMToEncoderUnitsInDecisecond(double RPM) {
        return (RPM * ENCODER_UNITS_PER_ROTATION) / MILLISECOND_TO_MINUTE;
    }

    public double encoderUnitsInDecisecondToRPM(double encoderUnits) {
        return (encoderUnits * MILLISECOND_TO_MINUTE) / ENCODER_UNITS_PER_ROTATION;
    }

    private double lastRPMError;

    public void initIsBallShot() {
        lastRPMError = Integer.MAX_VALUE;
    }

    public boolean updateIsBallShot() {
        boolean isBallShot = false;
        if (components.getShooterController().getCurrentError() >
                MIN_ERROR_RPM && components.getShooterController().getCurrentError() > lastRPMError) {
            isBallShot = true;
        }
        lastRPMError = components.getShooterController().getCurrentError();
        return isBallShot;
    }

    public boolean isShooterOnTarget() {
        return components.getShooterController().isOnTarget(RPMToEncoderUnitsInDecisecond(TOLERANCE_RPM));
    }
}

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
    private double lastRPMError;

    public Shooter(ShooterComponents components) {
        this.components = components;
        lastRPMError = Integer.MAX_VALUE;

        Shuffleboard.getTab("Shooter").addNumber("PID Error",
                () -> components.getMasterMotor().getClosedLoopError());
        Shuffleboard.getTab("Shooter").addNumber("Current Shooter Motor RPM",
                () -> encoderUnitsInDecisecondToRPM(components.getEncoder().getRate()));

        KP = Shuffleboard.getTab("Shooter").add("KP",
                components.getController().getPIDFTerms().getKp()).getEntry();
        KI = Shuffleboard.getTab("Shooter").add("KI",
                components.getController().getPIDFTerms().getKi()).getEntry();
        KD = Shuffleboard.getTab("Shooter").add("KD",
                components.getController().getPIDFTerms().getKd()).getEntry();
        KF = Shuffleboard.getTab("Shooter").add("KF",
                components.getController().getPIDFTerms().getKf()).getEntry();
    }

    @Override
    public void periodic() {
        components.getController().setPIDFTerms(
                KP.getDouble(components.getController().getPIDFTerms().getKp()),
                KI.getDouble(components.getController().getPIDFTerms().getKi()),
                KD.getDouble(components.getController().getPIDFTerms().getKd()),
                KF.getDouble(components.getController().getPIDFTerms().getKf()));
    }

    public void moveBySpeed(double speed) {
        components.getMasterMotor().set(speed);
    }

    public void stop() {
        moveBySpeed(0);
        components.getController().disable();
    }

    public void initMoveByRPM(double RPM) {
        components.getController().setSetpoint(RPMToEncoderUnitsInDecisecond(RPM));
        components.getController().enable();
    }

    public void updateMoveByRPM(double RPM) {
        components.getController().update(RPMToEncoderUnitsInDecisecond(RPM));
    }

    public double distanceMeterToEncoderUnitInDecisecond(double distance) { //TODO fix formula
        double encoderUnitsTarget;
        if (distance > MIDDLE_DISTANCE) {
            encoderUnitsTarget = ShooterConstants.ShooterCalculation.FORMULA_DISTANCE_FAR(distance);
        } else {
            encoderUnitsTarget = ShooterConstants.ShooterCalculation.FORMULA_DISTANCE_CLOSE(distance);
        }
        return Math.min(encoderUnitsTarget, MAX_VELOCITY);
    }

    public double RPMToEncoderUnitsInDecisecond(double RPM) {
        return (RPM * ENCODER_UNITS_PER_ROTATION) / DECISECOND_IN_MIN;
    }

    public double encoderUnitsInDecisecondToRPM(double encoderUnits) {
        return (encoderUnits * DECISECOND_IN_MIN) / ENCODER_UNITS_PER_ROTATION;
    }

    public void initIsBallShot() {
        lastRPMError = Integer.MAX_VALUE;
    }

    public boolean updateIsBallShot() {
        boolean isBallShot = false;
        if (components.getController().getCurrentError() >
                MIN_ERROR_RPM && components.getController().getCurrentError() > lastRPMError) {
            isBallShot = true;
        }
        lastRPMError = components.getController().getCurrentError();
        return isBallShot;
    }

    public boolean isOnTarget() {
        return components.getController().isOnTarget(RPMToEncoderUnitsInDecisecond(TOLERANCE_RPM));
    }
}

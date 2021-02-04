package frc.robot.shooter;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.shooter.ShooterConstants.*;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.MAX_VELOCITY;

public class Shooter extends SubsystemBase {

    private final ShooterComponents components;
    private final NetworkTableEntry kP;
    private final NetworkTableEntry kI;
    private final NetworkTableEntry kD;
    private final NetworkTableEntry kF;
    private double lastRPMError;

    public Shooter(ShooterComponents components) {
        this.components = components;
        initIsBallShot();

        Shuffleboard.getTab("Shooter").addNumber("PID Error",
                () -> components.getMasterMotor().getClosedLoopError());
        Shuffleboard.getTab("Shooter").addNumber("Current Shooter Motor RPM",
                () -> encoderUnitsInDecisecondToRPM(components.getEncoder().getRate()));

        kP = Shuffleboard.getTab("Shooter").add("kP",
                components.getController().getPIDFTerms().getKp()).getEntry();
        kI = Shuffleboard.getTab("Shooter").add("kI",
                components.getController().getPIDFTerms().getKi()).getEntry();
        kD = Shuffleboard.getTab("Shooter").add("kD",
                components.getController().getPIDFTerms().getKd()).getEntry();
        kF = Shuffleboard.getTab("Shooter").add("kF",
                components.getController().getPIDFTerms().getKf()).getEntry();
    }

    @Override
    public void periodic() {
        components.getController().setPIDFTerms(
                kP.getDouble(components.getController().getPIDFTerms().getKp()),
                kI.getDouble(components.getController().getPIDFTerms().getKi()),
                kD.getDouble(components.getController().getPIDFTerms().getKd()),
                kF.getDouble(components.getController().getPIDFTerms().getKf()));
    }

    public void moveBySpeed(double speed) {
        components.getMasterMotor().set(speed);
    }

    public void stop() {
        moveBySpeed(0);
        components.getController().disable();
    }

    public void initMoveByRPM(double rpm) {
        components.getController().setSetpoint(RPMToEncoderUnitsInDecisecond(rpm));
        components.getController().enable();
    }

    public void updateMoveByRPM(double rpm) {
        components.getController().update(RPMToEncoderUnitsInDecisecond(rpm));
    }

    public double distanceMetersToEncoderUnitsInDecisecond(double distance) { //TODO fix formula
        double encoderUnitsTarget;
        if (distance > MIDDLE_DISTANCE) {
            encoderUnitsTarget = ShooterConstants.ShooterCalculation.FORMULA_DISTANCE_FAR(distance);
        } else {
            encoderUnitsTarget = ShooterConstants.ShooterCalculation.FORMULA_DISTANCE_CLOSE(distance);
        }
        return Math.min(encoderUnitsTarget, MAX_VELOCITY);
    }

    public double RPMToEncoderUnitsInDecisecond(double rpm) {
        return (rpm * ENCODER_UNITS_PER_ROTATION) / DECISECOND_IN_MIN;
    }

    public double encoderUnitsInDecisecondToRPM(double encoderUnits) {
        return (encoderUnits * DECISECOND_IN_MIN) / ENCODER_UNITS_PER_ROTATION;
    }

    public void initIsBallShot() {
        lastRPMError = Double.MAX_VALUE;
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

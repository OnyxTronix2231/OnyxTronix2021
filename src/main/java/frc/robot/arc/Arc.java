package frc.robot.arc;

import static frc.robot.arc.ArcConstants.ANGLE_PER_MOTOR_ROTATION;
import static frc.robot.arc.ArcConstants.ENCODER_UNITS_PER_ROTATION;
import static frc.robot.arc.ArcConstants.MAX_POSSIBLE_ANGLE;
import static frc.robot.arc.ArcConstants.MIN_POSSIBLE_ANGLE;
import static frc.robot.arc.ArcConstants.MOVING_TOLERANCE_ENCODER_UNITS;
import static frc.robot.arc.ArcConstants.OFFSET;
import static frc.robot.arc.ArcConstants.TOLERANCE_ANGLE;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arc extends SubsystemBase {

    private final ArcComponents components;
    private final NetworkTableEntry kP;
    private final NetworkTableEntry kI;
    private final NetworkTableEntry kD;
    private final NetworkTableEntry kF;
    private final NetworkTableEntry cruiseVelocity;
    private final NetworkTableEntry acceleration;
    private final NetworkTableEntry accelerationSmoothing;

    public Arc(ArcComponents components) {
        this.components = components;

        Shuffleboard.getTab("Arc").addNumber("Current velocity",
                () -> components.getEncoder().getRate());
        Shuffleboard.getTab("Arc").addNumber("current position",
                () -> encoderUnitsToAngle(components.getEncoder().getCount()));

        kP = Shuffleboard.getTab("Arc").add("kP",
                components.getController().getPIDFTerms().getKp()).getEntry();
        kI = Shuffleboard.getTab("Arc").add("kI",
                components.getController().getPIDFTerms().getKi()).getEntry();
        kD = Shuffleboard.getTab("Arc").add("kD",
                components.getController().getPIDFTerms().getKd()).getEntry();
        kF = Shuffleboard.getTab("Arc").add("kF",
                components.getController().getPIDFTerms().getKf()).getEntry();
        cruiseVelocity = Shuffleboard.getTab("Arc").add("Cruise velocity",
                components.getController().getCruiseVelocity()).getEntry();
        acceleration = Shuffleboard.getTab("Arc").add("Acceleration",
                components.getController().getAcceleration()).getEntry();
        accelerationSmoothing = Shuffleboard.getTab("Arc").add("Acceleration smoothing",
                components.getController().getAccelerationSmoothing()).getEntry();
    }

    @Override
    public void periodic() {
        components.getController().setPIDFTerms(
                kP.getDouble(components.getController().getPIDFTerms().getKp()),
                kI.getDouble(components.getController().getPIDFTerms().getKi()),
                kD.getDouble(components.getController().getPIDFTerms().getKd()),
                kF.getDouble(components.getController().getPIDFTerms().getKf()));
        components.getController().setCruiseVelocity((int)
                cruiseVelocity.getDouble(components.getController().getCruiseVelocity()));
        components.getController().setAcceleration((int)
                acceleration.getDouble(components.getController().getAcceleration()));
        components.getController().setAccelerationSmoothing((int)
                accelerationSmoothing.getDouble(components.getController().getAccelerationSmoothing()));
    }

    public void moveBySpeed(double speed) {
        components.getMotor().set(speed);
    }

    public void stop() {
        moveBySpeed(0);
        components.getController().disable();
    }

    public void initMoveToAngle(double angle) {
        angle = getValidAngle(angle);
        components.getController().setSetpoint(angleToEncoderUnits(angle));
        components.getController().enable();
    }

    public void updateMoveToAngle(double angle) {
        angle = getValidAngle(angle);
        components.getController().update(angleToEncoderUnits(angle));
    }

    public double distanceMetersToAngle(double distance) { //TODO add formula
        System.err.println("there is no formula");
        return distance;
    }

    public double angleToEncoderUnits(double angle) {
        return (angle / ANGLE_PER_MOTOR_ROTATION) * ENCODER_UNITS_PER_ROTATION;
    }

    public double encoderUnitsToAngle(double encoderUnits) {
        return (encoderUnits / ENCODER_UNITS_PER_ROTATION) * ANGLE_PER_MOTOR_ROTATION;
    }

    public double getValidAngle(double angle) {
        angle = angle - OFFSET;
        return Math.min(MAX_POSSIBLE_ANGLE, Math.max(angle, MIN_POSSIBLE_ANGLE));
    }

    public boolean isOnTarget() {
        return components.getController().isOnTarget(angleToEncoderUnits(TOLERANCE_ANGLE))
                || hasHitForwardLimit() || hasHitReverseLimit();
    }

    public boolean hasHitForwardLimit() {
        return components.getForwardLimitSwitch().isOpen();
    }

    public boolean hasHitReverseLimit() {
        return components.getReverseLimitSwitch().isOpen();
    }

    public void resetEncoder() {
        components.getEncoder().reset();
    }

    public void disableLimitSwitches() {
        components.getMotor().overrideLimitSwitchesEnable(false);
    }

    public void enableLimitSwitches() {
        components.getMotor().overrideLimitSwitchesEnable(true);
    }

    public boolean isMoving() {
        return Math.abs(components.getEncoder().getRate()) > MOVING_TOLERANCE_ENCODER_UNITS;
    }
}

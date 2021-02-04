package frc.robot.arc;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.arc.ArcConstants.*;

public class Arc extends SubsystemBase {

    private final ArcComponents components;
    private final NetworkTableEntry KP;
    private final NetworkTableEntry KI;
    private final NetworkTableEntry KD;
    private final NetworkTableEntry KF;
    private final NetworkTableEntry cruiseVelocity;
    private final NetworkTableEntry acceleration;
    private final NetworkTableEntry accelerationSmoothing;

    public Arc(ArcComponents components) {
        this.components = components;
        
        Shuffleboard.getTab("Arc").addNumber("Current velocity",
                () -> components.getEncoder().getRate());
        Shuffleboard.getTab("Arc").addNumber("current position",
                () -> encoderUnitsToAngle(components.getEncoder().getCount()));

        KP = Shuffleboard.getTab("Arc").add("KP",
                components.getController().getPIDFTerms().getKp()).getEntry();
        KI = Shuffleboard.getTab("Arc").add("KI",
                components.getController().getPIDFTerms().getKi()).getEntry();
        KD = Shuffleboard.getTab("Arc").add("KD",
                components.getController().getPIDFTerms().getKd()).getEntry();
        KF = Shuffleboard.getTab("Arc").add("KF",
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
                KP.getDouble(components.getController().getPIDFTerms().getKp()),
                KI.getDouble(components.getController().getPIDFTerms().getKi()),
                KD.getDouble(components.getController().getPIDFTerms().getKd()),
                KF.getDouble(components.getController().getPIDFTerms().getKf()));
        components.getController().setCruiseVelocity((int)
                cruiseVelocity.getDouble(components.getController().getCruiseVelocity()));
        components.getController().setAcceleration((int)
                acceleration.getDouble(components.getController().getAcceleration()));
        components.getController().setAccelerationSmoothing((int)
                accelerationSmoothing.getDouble(components.getController().getAccelerationSmoothing()));
    }

    public void moveBySpeed(double speed) {
        components.getMasterMotor().set(speed);
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

    public double distanceMeterToAngle(double distance) { //TODO add formula
        return distance;
    }

    public double angleToEncoderUnits(double angle) {
        return (angle / ANGLE_PER_MOTOR_ROTATION) * ENCODER_UNITS_PER_ROTATION;
    }

    public double encoderUnitsToAngle(double encoderUnits) {
        return (encoderUnits / ANGLE_PER_MOTOR_ROTATION) * ENCODER_UNITS_PER_ROTATION;
    }

    public double getValidAngle(double angle) {
        return Math.min(MAX_POSSIBLE_ANGLE, Math.max(angle, MIN_POSSIBLE_ANGLE));
    }

    public boolean isOnTarget() {
        return components.getController().isOnTarget(angleToEncoderUnits(TOLERANCE_ANGLE))
                || hasHitUpperLimit() || hasHitLowerLimit();
    }

    public boolean hasHitUpperLimit(){
        return components.getForwardLimitSwitch().isOpen();
    }

    public boolean hasHitLowerLimit(){
        return components.getReverseLimitSwitch().isOpen();
    }

    public void resetEncoder(){
        components.getEncoder().reset();
    }

    public void disableLimitSwitches(){
        components.getMasterMotor().overrideLimitSwitchesEnable(false);
    }

    public void enableLimitSwitches(){
        components.getMasterMotor().overrideLimitSwitchesEnable(true);
    }

    public boolean isMoving(){
     return Math.abs(components.getEncoder().getRate()) < MOVING_TOLERANCE_ENCODER_UNITS;
    }
}

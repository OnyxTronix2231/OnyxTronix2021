package frc.robot.arc;

import static frc.robot.arc.ArcConstants.ANGLE_PER_MOTOR_ROTATION;
import static frc.robot.arc.ArcConstants.ENCODER_UNITS_PER_ROTATION;
import static frc.robot.arc.ArcConstants.MAX_POSSIBLE_ANGLE;
import static frc.robot.arc.ArcConstants.MIN_POSSIBLE_ANGLE;
import static frc.robot.arc.ArcConstants.OFFSET;
import static frc.robot.arc.ArcConstants.REAL_MAX_POSSIBLE_ANGLE;
import static frc.robot.arc.ArcConstants.START_ENCODER_VALUE;
import static frc.robot.arc.ArcConstants.TIME_OUT;
import static frc.robot.arc.ArcConstants.TOLERANCE_ANGLE;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.arc.ArcConstants.ArcCalculation;

public class Arc extends SubsystemBase {

    private final ArcComponents components;
//    private final NetworkTableEntry kP;
//    private final NetworkTableEntry kI;
//    private final NetworkTableEntry kD;
//    private final NetworkTableEntry kF;
//    private final NetworkTableEntry cruiseVelocity;
//    private final NetworkTableEntry acceleration;
//    private final NetworkTableEntry accelerationSmoothing;
//    private final NetworkTableEntry angleTest;

    public Arc(ArcComponents components) {
        this.components = components;

        components.getMotor().configForwardSoftLimitEnable(true);
        components.getMotor().configForwardSoftLimitThreshold(angleToEncoderUnits(MAX_POSSIBLE_ANGLE));
        components.getMotor().configReverseSoftLimitEnable(true);
        components.getMotor().configReverseSoftLimitThreshold(angleToEncoderUnits(MIN_POSSIBLE_ANGLE));

        resetEncoderByAbsoluteValue();

//        Shuffleboard.getTab("Arc").addNumber("Current velocity",
//                () -> components.getEncoder().getRate());
//        Shuffleboard.getTab("Arc").addNumber("current angle",
//                this::getAngle);
//        Shuffleboard.getTab("Arc").addNumber("current position ENC",
//                ()-> components.getEncoder().getCount());
//        Shuffleboard.getTab("Arc").addNumber("current ERROR ENC",
//                () -> encoderUnitsToAngle(components.getMotor().getClosedLoopError()));

//        kP = Shuffleboard.getTab("Arc").add("kP",
//                components.getController().getPIDFTerms().getKp()).getEntry();
//        kI = Shuffleboard.getTab("Arc").add("kI",
//                components.getController().getPIDFTerms().getKi()).getEntry();
//        kD = Shuffleboard.getTab("Arc").add("kD",
//                components.getController().getPIDFTerms().getKd()).getEntry();
//        kF = Shuffleboard.getTab("Arc").add("kF",
//                components.getController().getPIDFTerms().getKf()).getEntry();
//        angleTest= Shuffleboard.getTab("Arc").add("arc Test", 21).getEntry();
//
//        cruiseVelocity = Shuffleboard.getTab("Arc").add("Cruise velocity",
//                components.getController().getCruiseVelocity()).getEntry();
//        acceleration = Shuffleboard.getTab("Arc").add("Acceleration",
//                components.getController().getAcceleration()).getEntry();
//        accelerationSmoothing = Shuffleboard.getTab("Arc").add("Acceleration smoothing",
//                components.getController().getAccelerationSmoothing()).getEntry();
    }

    @Override
    public void periodic() {
//        components.getController().setPIDFTerms(
//                kP.getDouble(components.getController().getPIDFTerms().getKp()),
//                kI.getDouble(components.getController().getPIDFTerms().getKi()),
//                kD.getDouble(components.getController().getPIDFTerms().getKd()),
//                kF.getDouble(components.getController().getPIDFTerms().getKf()));
//        components.getController().setCruiseVelocity((int)
//                cruiseVelocity.getDouble(components.getController().getCruiseVelocity()));
//        components.getController().setAcceleration((int)
//                acceleration.getDouble(components.getController().getAcceleration()));
//        components.getController().setAccelerationSmoothing((int)
//                accelerationSmoothing.getDouble(components.getController().getAccelerationSmoothing()));
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

    public void configReverseSoftLimitEnable(boolean enable) {
        components.getMotor().configReverseSoftLimitEnable(enable);
    }

    public double distanceMetersToAngle(double distance) { //TODO add formula
        return Math.min(ArcCalculation.FORMULA_DISTANCE_FAR(distance), REAL_MAX_POSSIBLE_ANGLE);
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

    public double getAngle() {
        return encoderUnitsToAngle(components.getEncoder().getCount()) + OFFSET;
    }

    public boolean isOnTarget() {
        return components.getController().isOnTarget(angleToEncoderUnits(TOLERANCE_ANGLE));
    }

    public boolean hasHitForwardLimit() {
        return components.getForwardLimitSwitch().isOpen();
    }

    public boolean hasHitReverseLimit() {
        return components.getReverseLimitSwitch().isOpen();
    }

    public void resetEncoderByAbsoluteValue() {
        components.getMotor().getSensorCollection().setPulseWidthPosition(0, TIME_OUT);
        components.getMotor().setSelectedSensorPosition(components.getMotor().getSensorCollection().
                getPulseWidthPosition() - START_ENCODER_VALUE);
    }

//    public double getTestAngle(){
//        return angleTest.getDouble(20);
//    }
}

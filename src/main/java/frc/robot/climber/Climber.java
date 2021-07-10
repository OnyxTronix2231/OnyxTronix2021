package frc.robot.climber;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.climber.ClimberConstants.ClimberConstantsA.*;
//import static frc.robot.climber.ClimberConstants.DECISECONDS_IN_MIN;
//import static frc.robot.climber.ClimberConstants.ENCODER_UNITS_PER_ROTATION;

public class Climber extends SubsystemBase {

    private final ClimberComponents components;
//    private final NetworkTableEntry kP;
//    private final NetworkTableEntry kI;
//    private final NetworkTableEntry kD;
//    private final NetworkTableEntry kF;

    public Climber(ClimberComponents components) {
        this.components = components;

        components.getMasterMotor().configForwardSoftLimitEnable(true);
        components.getMasterMotor().configForwardSoftLimitThreshold(MAX_POSSIBLE_DISTANCE);
        components.getMasterMotor().configReverseSoftLimitEnable(true);
        components.getMasterMotor().configReverseSoftLimitThreshold(MIN_POSSIBLE_DISTANCE);

//        Shuffleboard.getTab("Climber").addNumber("current pos", ()-> components.getEncoder().getCount());
//        Shuffleboard.getTab("Climber").addNumber("current velocity", ()-> components.getEncoder().getRate());

//        kP = Shuffleboard.getTab("Climber").add("kP",
//            components.getController().getPIDFTerms().getKp()).getEntry();
//        kI = Shuffleboard.getTab("Climber").add("kI",
//            components.getController().getPIDFTerms().getKi()).getEntry();
//        kD = Shuffleboard.getTab("Climber").add("kD",
//            components.getController().getPIDFTerms().getKd()).getEntry();
//        kF = Shuffleboard.getTab("Climber").add("kF",
//            components.getController().getPIDFTerms().getKf()).getEntry();
    }

    @Override
    public void periodic() {
//        components.getController().setPIDFTerms(
//            kP.getDouble(components.getController().getPIDFTerms().getKp()),
//            kI.getDouble(components.getController().getPIDFTerms().getKi()),
//            kD.getDouble(components.getController().getPIDFTerms().getKd()),
//            kF.getDouble(components.getController().getPIDFTerms().getKf()));
    }

    public void moveBySpeed(double speed) {
        components.getMasterMotor().set(speed);
    }

    public void initClimbToDistance(double distanceInEncoderUnits) {
        components.getController().setSetpoint(distanceInEncoderUnits);
        components.getController().enable();
    }

    public void updateClimbToDistance(double distanceInEncoderUnits) {
        components.getController().update(distanceInEncoderUnits);
    }

//    public double encoderUnitsInDecisecondToRPM(int encoderUnits) {
//        return (encoderUnits * DECISECONDS_IN_MIN) / ENCODER_UNITS_PER_ROTATION;
//    }

    public boolean isOnTarget() {
        return components.getController().isOnTarget(TOLERANCE);
    }

    public void stopMotor() {
        moveBySpeed(0);
        components.getController().disable();
    }
}

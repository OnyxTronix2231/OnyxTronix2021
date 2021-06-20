package frc.robot.climber;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.climber.ClimberConstants.ClimberConstantsA.TOLERANCE;
import static frc.robot.climber.ClimberConstants.DISTANCE_PER_MOTOR_ROTATION;
import static frc.robot.climber.ClimberConstants.ENCODER_UNITS_PER_ROTATION;

public class Climber extends SubsystemBase {

    private final ClimberComponents components;
    private final NetworkTableEntry kP;
    private final NetworkTableEntry kI;
    private final NetworkTableEntry kD;
    private final NetworkTableEntry kF;

    public Climber(ClimberComponents components) {
        this.components = components;
        kP = Shuffleboard.getTab("Climber").add("kP",
            components.getController().getPIDFTerms().getKp()).getEntry();
        kI = Shuffleboard.getTab("Climber").add("kI",
            components.getController().getPIDFTerms().getKi()).getEntry();
        kD = Shuffleboard.getTab("Climber").add("kD",
            components.getController().getPIDFTerms().getKd()).getEntry();
        kF = Shuffleboard.getTab("Climber").add("kF",
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

    public void initClimbToDistance(double distance) {
        components.getController().setSetpoint(rpmToEncoderUnitsInDecisecond(distance)); // Todo: convert to encoder units
        components.getController().enable();
    }

    public void updateClimbToDistance(double distance) {
        components.getController().update(distance); // Todo: convert to encoder units
    }

    public double rpmToEncoderUnitsInDecisecond(double distance) {
        return (distance * DISTANCE_PER_MOTOR_ROTATION) / ENCODER_UNITS_PER_ROTATION;
    }

    public boolean isOnTarget() {
        return components.getController().isOnTarget(TOLERANCE); // Todo: convert to encoder units, put variable
    }

    public void stopMotor() {
        moveBySpeed(0);
        components.getController().disable();
    }
}

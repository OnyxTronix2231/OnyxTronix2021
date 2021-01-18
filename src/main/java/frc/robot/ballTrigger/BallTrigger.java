package frc.robot.ballTrigger;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.ballTrigger.BallTriggerConstants.ENCODER_UNITS_PER_ROTATION;
import static frc.robot.ballTrigger.BallTriggerConstants.HUNDREDS_OF_MILISECS_IN_MIN;
import static frc.robot.ballTrigger.BallTriggerConstants.OPEN_PISTON;

public class BallTrigger extends SubsystemBase {

    private final BallTriggerComponents components;
    NetworkTableEntry kP;
    NetworkTableEntry kI;
    NetworkTableEntry kD;
    NetworkTableEntry kF;

    public BallTrigger(BallTriggerComponents components) {
        this.components = components;
        Shuffleboard.getTab("Trigger").addNumber("Current error",
                () -> components.getMasterMotor().getClosedLoopError());
        Shuffleboard.getTab("Trigger").addNumber("Current RPM",
                () -> encoderUnitsToRPM(components.getMasterMotor().getSelectedSensorVelocity()));
        Shuffleboard.getTab("Trigger").addNumber("Current velocity in encoder units",
                () -> components.getMasterMotor().getSelectedSensorVelocity());

        kP = Shuffleboard.getTab("Trigger").add("kP",
                components.getPIDController().getPIDFTerms().getKp()).getEntry();

        kI = Shuffleboard.getTab("Trigger").add("kI",
                components.getPIDController().getPIDFTerms().getKp()).getEntry();

        kD = Shuffleboard.getTab("Trigger").add("kD",
                components.getPIDController().getPIDFTerms().getKp()).getEntry();

        kF = Shuffleboard.getTab("Trigger").add("kF",
                components.getPIDController().getPIDFTerms().getKp()).getEntry();
    }

    @Override
    public void periodic() {
        components.getPIDController().setPIDFTerms(kP.getDouble(components.getPIDController().getPIDFTerms().getKp()),
                kI.getDouble(components.getPIDController().getPIDFTerms().getKi()),
                kD.getDouble(components.getPIDController().getPIDFTerms().getKd()),
                kF.getDouble(components.getPIDController().getPIDFTerms().getKf()));
    }

    public void moveBySpeed(double speed) {
        components.getMasterMotor().set(speed);
    }

    public void initMoveByRPM(double RPM) {
        components.getPIDController().setSetpoint(RPMToEncoderUnits(RPM));
        components.getPIDController().enable();
    }

    public void updateMoveByRPM(double RPM) {
        components.getPIDController().update(RPMToEncoderUnits(RPM));
    }

    public double RPMToEncoderUnits(double RPM) {
        return RPM * ENCODER_UNITS_PER_ROTATION / HUNDREDS_OF_MILISECS_IN_MIN;
    }

    public double encoderUnitsToRPM(double encoderUnits) {
        return (encoderUnits * HUNDREDS_OF_MILISECS_IN_MIN) / ENCODER_UNITS_PER_ROTATION;
    }

    public void stop() {
        moveBySpeed(0);
    }

    public void openPistons() {
        components.getSolenoid().set(OPEN_PISTON);
    }

    public void closePistons() {
        components.getSolenoid().set(!OPEN_PISTON);
    }
}

package frc.robot.revolver;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.revolver.RevolverConstants.ENCODER_UNITS_PER_ROTATION;
import static frc.robot.revolver.RevolverConstants.DECISECOND_IN_MIN;
import static frc.robot.revolver.RevolverConstants.TOLERANCE_IN_RPM;

public class Revolver extends SubsystemBase {

    private final RevolverComponents components;
    private final NetworkTableEntry kP;
    private final NetworkTableEntry kI;
    private final NetworkTableEntry kD;
    private final NetworkTableEntry kF;

    public Revolver(RevolverComponents components) {
        this.components = components;
        Shuffleboard.getTab("Revolver").addNumber("Current error",
                () -> components.getMotor().getClosedLoopError());
        Shuffleboard.getTab("Revolver").addNumber("Current RPM",
                () -> encoderUnitsInDecisecondToRPM(components.getMotor().getSelectedSensorVelocity()));
        Shuffleboard.getTab("Revolver").addNumber("Current velocity in encoder units",
                () -> components.getMotor().getSelectedSensorVelocity());

        kP = Shuffleboard.getTab("Revolver").add("kP",
                components.getPIDController().getPIDFTerms().getKp()).getEntry();

        kI = Shuffleboard.getTab("Revolver").add("kI",
                components.getPIDController().getPIDFTerms().getKp()).getEntry();

        kD = Shuffleboard.getTab("Revolver").add("kD",
                components.getPIDController().getPIDFTerms().getKp()).getEntry();

        kF = Shuffleboard.getTab("Revolver").add("kF",
                components.getPIDController().getPIDFTerms().getKp()).getEntry();
    }

    @Override
    public void periodic() {
        components.getPIDController().setPIDFTerms(
                kP.getDouble(components.getPIDController().getPIDFTerms().getKp()),
                kI.getDouble(components.getPIDController().getPIDFTerms().getKi()),
                kD.getDouble(components.getPIDController().getPIDFTerms().getKd()),
                kF.getDouble(components.getPIDController().getPIDFTerms().getKf()));
    }

    public void moveBySpeed(double speed) {
        components.getMotor().set(speed);
    }

    public void initMoveByRPM(double rpm) {
        components.getPIDController().setSetpoint(rpmToEncoderUnitInDecisecond(rpm));
        components.getPIDController().enable();
    }

    public void updateMoveByRPM(double rpm) {
        components.getPIDController().update(rpmToEncoderUnitInDecisecond(rpm));
    }

    public void stop() {
        moveBySpeed(0);
        components.getPIDController().disable();
    }

    public boolean isOnTarget() {
        return components.getPIDController().isOnTarget(rpmToEncoderUnitInDecisecond(TOLERANCE_IN_RPM));
    }

    public double rpmToEncoderUnitInDecisecond(double rpm) {
        return rpm * ENCODER_UNITS_PER_ROTATION / DECISECOND_IN_MIN;
    }

    public double encoderUnitsInDecisecondToRPM(double encoderUnits) {
        return (encoderUnits * DECISECOND_IN_MIN) / ENCODER_UNITS_PER_ROTATION;
    }
}

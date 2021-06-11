package frc.robot.revolver;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.revolver.RevolverConstants.ENCODER_UNITS_PER_ROTATION;
import static frc.robot.revolver.RevolverConstants.DECISECOND_IN_MIN;
import static frc.robot.revolver.RevolverConstants.RevolverComponentsA.REGULAR_AMP;
import static frc.robot.revolver.RevolverConstants.TOLERANCE_IN_RPM;

public class Revolver extends SubsystemBase {

    private final RevolverComponents components;
    private final NetworkTableEntry kpEntry;
    private final NetworkTableEntry kiEntry;
    private final NetworkTableEntry kdEntry;
    private final NetworkTableEntry kfEntry;

    public Revolver(RevolverComponents components) {
        this.components = components;
        Shuffleboard.getTab("Revolver").addNumber("Current error in encoder units",
                () -> components.getMotor().getClosedLoopError());
        Shuffleboard.getTab("Revolver").addNumber("Current error in RPM",
                () -> encoderUnitsInDecisecondToRPM(components.getMotor().getClosedLoopError()));
        Shuffleboard.getTab("Revolver").addNumber("Current velocity in encoder units",
                () -> components.getMotor().getSelectedSensorVelocity());
        Shuffleboard.getTab("Revolver").addNumber("Current RPM",
                () -> encoderUnitsInDecisecondToRPM(components.getMotor().getSelectedSensorVelocity()));

        kpEntry = Shuffleboard.getTab("Revolver").add("kP",
                components.getPIDController().getPIDFTerms().getKp()).getEntry();

        kiEntry = Shuffleboard.getTab("Revolver").add("kI",
                components.getPIDController().getPIDFTerms().getKi()).getEntry();

        kdEntry = Shuffleboard.getTab("Revolver").add("kD",
                components.getPIDController().getPIDFTerms().getKd()).getEntry();

        kfEntry = Shuffleboard.getTab("Revolver").add("kF",
                components.getPIDController().getPIDFTerms().getKf()).getEntry();
    }

    @Override
    public void periodic() {
        components.getPIDController().setPIDFTerms(
                kpEntry.getDouble(components.getPIDController().getPIDFTerms().getKp()),
                kiEntry.getDouble(components.getPIDController().getPIDFTerms().getKi()),
                kdEntry.getDouble(components.getPIDController().getPIDFTerms().getKd()),
                kfEntry.getDouble(components.getPIDController().getPIDFTerms().getKf()));
    }

    public void moveBySpeed(double speed) {
        components.getMotor().set(speed);
    }

    public void initMoveByRPM(double rpm) {
        components.getPIDController().setSetpoint(rpmToEncoderUnitInDecisecond(rpm));
        components.getPIDController().enable();
    }

    public void updateMoveByRPM(double rpm) {
        System.out.println(rpmToEncoderUnitInDecisecond(rpm));
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

    public boolean isStuck () {
        return components.getMotor().getSupplyCurrent() > REGULAR_AMP;
    }
}

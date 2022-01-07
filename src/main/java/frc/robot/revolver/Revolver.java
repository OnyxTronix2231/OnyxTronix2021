package frc.robot.revolver;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.revolver.RevolverConstants.*;
import static frc.robot.revolver.RevolverConstants.RevolverComponents.REGULAR_AMP;

public class Revolver extends SubsystemBase {

    private final RevolverComponents components;
//    private final NetworkTableEntry kpEntry;
//    private final NetworkTableEntry kiEntry;
//    private final NetworkTableEntry kdEntry;
//    private final NetworkTableEntry kfEntry;

    public Revolver(RevolverComponents components) {
        this.components = components;
        resetEncoder();
//        Shuffleboard.getTab("Revolver").addNumber("Current error in encoder units",
//                () -> components.getMotor().getClosedLoopError());
//        Shuffleboard.getTab("Revolver").addNumber("Current error in RPM",
//                () -> encoderUnitsInDecisecondToRPM(components.getMotor().getClosedLoopError()));
//        Shuffleboard.getTab("Revolver").addNumber("Current velocity in encoder units",
//                () -> components.getMotor().getSelectedSensorVelocity());
//        Shuffleboard.getTab("Revolver").addNumber("Current RPM",
//                () -> encoderUnitsInDecisecondToRPM(components.getMotor().getSelectedSensorVelocity()));

//        kpEntry = Shuffleboard.getTab("Revolver").add("kP",
//                components.getPIDController().getPIDFTerms().getKp()).getEntry();
//
//        kiEntry = Shuffleboard.getTab("Revolver").add("kI",
//                components.getPIDController().getPIDFTerms().getKi()).getEntry();
//
//        kdEntry = Shuffleboard.getTab("Revolver").add("kD",
//                components.getPIDController().getPIDFTerms().getKd()).getEntry();
//
//        kfEntry = Shuffleboard.getTab("Revolver").add("kF",
//                components.getPIDController().getPIDFTerms().getKf()).getEntry();
    }

    @Override
    public void periodic() {
//        components.getPIDController().setPIDFTerms(
//                kpEntry.getDouble(components.getPIDController().getPIDFTerms().getKp()),
//                kiEntry.getDouble(components.getPIDController().getPIDFTerms().getKi()),
//                kdEntry.getDouble(components.getPIDController().getPIDFTerms().getKd()),
//                kfEntry.getDouble(components.getPIDController().getPIDFTerms().getKf()));
    }

    public void moveBySpeed(double speed) {
        components.getMotor().set(speed);
    }

    public void initMoveByRPM(double rpm) {
        if (Math.abs(rpm) > MINIMUM_RPM_FOR_CLOSE_LOOP_RAMP) {
            components.getMotor().configClosedloopRamp(CLOSE_LOOP_RAMP_WHILE_SHOOTING);
        }
        components.getPIDController().setSetpoint(rpmToEncoderUnitInDecisecond(rpm));
        components.getPIDController().enable();
    }

    public void updateMoveByRPM(double rpm) {
        components.getPIDController().update(rpmToEncoderUnitInDecisecond(rpm));
    }

    public void stop() {
        moveBySpeed(0);
        components.getPIDController().disable();
        components.getMotor().configClosedloopRamp(0);
    }

    public boolean isOnTarget() {
        return components.getPIDController().isOnTarget(rpmToEncoderUnitInDecisecond(TOLERANCE_IN_RPM));
    }

    public boolean isHallEffectClosed() {
        return !components.getHallEffect().get();
    }

    public double rpmToEncoderUnitInDecisecond(double rpm) {
        return rpm * ENCODER_UNITS_PER_ROTATION / DECISECOND_IN_MIN;
    }

    public double encoderUnitsInDecisecondToRPM(double encoderUnits) {
        return (encoderUnits * DECISECOND_IN_MIN) / ENCODER_UNITS_PER_ROTATION;
    }

    public void resetEncoder() {
        components.getMotor().getSensorCollection().setIntegratedSensorPosition(0, 100);
        components.getMotor().setSelectedSensorPosition(0);
    }

    public boolean isHallEffectOnTarget() {
        double pos = components.getEncoder().getCount() % ENCODER_UNITS_PER_ROTATION;
        return pos > BALL_ONE_POS_FIRST && pos < BALL_ONE_POS_SECOND ||
                pos > BALL_TWO_POS_FIRST && pos < BALL_TWO_POS_SECOND ||
                pos > BALL_THIRD_POS_FIRST && pos < BALL_THIRD_POS_SECOND ||
                pos > BALL_FOURTH_POS_FIRST && pos < BALL_FOURTH_POS_SECOND ||
                pos > BALL_FIVE_POS_FIRST && pos < BALL_FIVE_POS_SECOND;
    }

    public boolean isStuck() {
        return components.getMotor().getSupplyCurrent() > REGULAR_AMP;
    }
}

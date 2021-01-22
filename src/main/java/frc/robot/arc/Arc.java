package frc.robot.arc;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.arc.arcConstants.*;

public class Arc extends SubsystemBase {

    private final ArcComponents components;
    private final NetworkTableEntry KP;
    private final NetworkTableEntry KI;
    private final NetworkTableEntry KD;
    private final NetworkTableEntry KF;

    public Arc(ArcComponents components) {
        this.components = components;
        Shuffleboard.getTab("Arc").addNumber("Current velocity",
                () -> components.getArcMotorEncoder().getRate());
        Shuffleboard.getTab("Arc").addNumber("current position",
                () -> encoderUnitsToAngle(components.getArcMotorEncoder().getCount()));

        KP = Shuffleboard.getTab("Arc").add("KP",
                components.getArcController().getPIDFTerms().getKp()).getEntry();
        KI = Shuffleboard.getTab("Arc").add("KI",
                components.getArcController().getPIDFTerms().getKi()).getEntry();
        KD = Shuffleboard.getTab("Arc").add("KD",
                components.getArcController().getPIDFTerms().getKd()).getEntry();
        KF = Shuffleboard.getTab("Arc").add("KF",
                components.getArcController().getPIDFTerms().getKf()).getEntry();
    }

    @Override
    public void periodic() {
        components.getArcController().setPIDFTerms(
                KP.getDouble(components.getArcController().getPIDFTerms().getKp()),
                KI.getDouble(components.getArcController().getPIDFTerms().getKi()),
                KD.getDouble(components.getArcController().getPIDFTerms().getKd()),
                KF.getDouble(components.getArcController().getPIDFTerms().getKf()));
    }

    public void moveArcBySpeed(double speed) {
        components.getMasterMotor().set(ControlMode.PercentOutput, speed);
    }

    public void stopArc() {
        components.getArcController().disable();
    }

    public void initMoveArcToAngle(double angle) {
        angle = getValidAngle(angle);
        components.getArcController().setSetpoint(angleToEncoderUnits(angle));
        components.getArcController().enable();
    }

    public void updateMoveArcToAngle(double angle) {
        angle = getValidAngle(angle);
        components.getArcController().update(angleToEncoderUnits(angle));
    }

    public double angleToEncoderUnits(double angle) {
        return ((angle / ANGLE_PER_MOTOR_ROTATION) * ENCODER_UNITS_PER_ROTATION) / ANGULAR_MOTOR_CONVERSION;
    }

    public double encoderUnitsToAngle(double encoderUnits) {
        return ((encoderUnits / ENCODER_UNITS_PER_ROTATION) * ANGLE_PER_MOTOR_ROTATION) / ANGULAR_MOTOR_CONVERSION;
    }

    public double getValidAngle(double angle) {
        return Math.min(MAX_POSSIBLE_ANGLE, Math.max(angle, MIN_POSSIBLE_ANGLE));
    }

    public boolean isOnTarget() {
        return components.getArcController().isOnTarget(angleToEncoderUnits(TOLERANCE_DEGREE));
    }
}

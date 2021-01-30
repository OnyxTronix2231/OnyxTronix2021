package frc.robot.arc;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
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
    }

    @Override
    public void periodic() {
        components.getController().setPIDFTerms(
                KP.getDouble(components.getController().getPIDFTerms().getKp()),
                KI.getDouble(components.getController().getPIDFTerms().getKi()),
                KD.getDouble(components.getController().getPIDFTerms().getKd()),
                KF.getDouble(components.getController().getPIDFTerms().getKf()));
    }

    public void moveArcBySpeed(double speed) {
        components.getMasterMotor().set(ControlMode.PercentOutput, speed);
    }

    public void stop() {
        moveArcBySpeed(0);
        components.getController().disable();
    }

    public void initMoveArcToAngle(double angle) {
        angle = getValidAngle(angle);
        components.getController().setSetpoint(angleToEncoderUnits(angle));
        components.getController().enable();
    }

    public void updateMoveArcToAngle(double angle) {
        angle = getValidAngle(angle);
        components.getController().update(angleToEncoderUnits(angle));
    }

    public double angleToEncoderUnits(double angle) {
        return ((angle / ANGLE_PER_MOTOR_ROTATION) * ENCODER_UNITS_PER_ROTATION) / ANGULAR_MOTOR_CONVERSION;
    }

    public double encoderUnitsToAngle(double encoderUnits) {
        return ((encoderUnits / ANGLE_PER_MOTOR_ROTATION) * ENCODER_UNITS_PER_ROTATION) / ANGULAR_MOTOR_CONVERSION;
    }

    public double getValidAngle(double angle) {
        return Math.min(MAX_POSSIBLE_ANGLE, Math.max(angle, MIN_POSSIBLE_ANGLE));
    }

    public boolean isOnTarget() {
        return components.getController().isOnTarget(angleToEncoderUnits(TOLERANCE_ANGLE));
    }

    public boolean limitForward(){
        return components.getForwardLimitSwitch().isOpen();
    }

    public boolean limitReverse(){
        return components.getReverseLimitSwitch().isOpen();
    }

    public boolean EndMovement(){
        return isOnTarget() || limitForward() || limitReverse();
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
}

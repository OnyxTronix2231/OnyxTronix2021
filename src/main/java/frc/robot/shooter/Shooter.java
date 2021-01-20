package frc.robot.shooter;

import static frc.robot.shooter.ShooterConstants.*;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.SHOOTER_MOTOR_MAX_VELOCITY;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    private final ShooterComponents components;
    private double lastRPMError;
    private final NetworkTableEntry shooterKp;
    private final NetworkTableEntry shooterKi;
    private final NetworkTableEntry shooterKd;
    private final NetworkTableEntry shooterKf;

    private final NetworkTableEntry angularKp;
    private final NetworkTableEntry angularKi;
    private final NetworkTableEntry angularKd;
    private final NetworkTableEntry angularKf;

    public Shooter(ShooterComponents components) {
        this.components = components;
        lastRPMError = Integer.MAX_VALUE;
        Shuffleboard.getTab("Shooter").addNumber("PID Error",
                () -> components.getMasterShooterMotor().getClosedLoopError());
        Shuffleboard.getTab("Shooter").addNumber("Current Shooter Motor RPM",
                () -> encoderUnitsInDecisecondToRPM(components.getMasterShooterMotor().getSelectedSensorVelocity()));
        Shuffleboard.getTab("Shooter").addNumber("Current velocity",
                () -> components.getAngularMotor().getSelectedSensorVelocity());
        Shuffleboard.getTab("Shooter").addNumber("current angular motor position", () ->
                encoderUnitsToAngle(components.getAngularMotor().getSelectedSensorPosition()));

        shooterKp = Shuffleboard.getTab("Shooter").add("shooterKp",
                components.getShooterController().getPIDFTerms().getKp()).getEntry();
        shooterKi = Shuffleboard.getTab("Shooter").add("shooterKi",
                components.getShooterController().getPIDFTerms().getKi()).getEntry();
        shooterKd = Shuffleboard.getTab("Shooter").add("shooterKd",
                components.getShooterController().getPIDFTerms().getKd()).getEntry();
        shooterKf = Shuffleboard.getTab("Shooter").add("shooterKf",
                components.getShooterController().getPIDFTerms().getKf()).getEntry();
        angularKp = Shuffleboard.getTab("Shooter").add("angularKp",
                components.getAngularController().getPIDFTerms().getKp()).getEntry();
        angularKi = Shuffleboard.getTab("Shooter").add("angularKi",
                components.getAngularController().getPIDFTerms().getKi()).getEntry();
        angularKd = Shuffleboard.getTab("Shooter").add("angularKd",
                components.getAngularController().getPIDFTerms().getKd()).getEntry();
        angularKf = Shuffleboard.getTab("Shooter").add("angularKf",
                components.getAngularController().getPIDFTerms().getKf()).getEntry();
    }

    @Override
    public void periodic() {
        components.getShooterController().setPIDFTerms(
                shooterKp.getDouble(components.getShooterController().getPIDFTerms().getKp()),
                shooterKi.getDouble(components.getShooterController().getPIDFTerms().getKi()),
                shooterKd.getDouble(components.getShooterController().getPIDFTerms().getKd()),
                shooterKf.getDouble(components.getShooterController().getPIDFTerms().getKf()));
        components.getAngularController().setPIDFTerms(
                angularKp.getDouble(components.getAngularController().getPIDFTerms().getKp()),
                angularKi.getDouble(components.getAngularController().getPIDFTerms().getKi()),
                angularKd.getDouble(components.getAngularController().getPIDFTerms().getKd()),
                angularKf.getDouble(components.getAngularController().getPIDFTerms().getKf()));
    }

    public void moveShooterBySpeed(double speed) {
        components.getMasterShooterMotor().set(speed);
    }

    public void stopShooterMotor() {
        moveShooterBySpeed(0);
    }

    public void initMoveShooterByRPM(double RPM) {
        components.getShooterController().setSetpoint(RPMToEncoderUnitsInDecisecond(RPM));
        components.getShooterController().enable();
    }

    public void updateMoveShooterByRPM(double RPM) {
        components.getShooterController().update(RPMToEncoderUnitsInDecisecond(RPM));
    }

    public void moveAngularMotorBySpeed(double speed) {
        components.getAngularMotor().set(ControlMode.PercentOutput, speed);
    }

    public void stopAngularMotor() {
        moveAngularMotorBySpeed(0);
    }

    public void initAngualrMoveToAngle(double angle) {
        angle = getValidAngle(angle);
        components.getAngularController().setSetpoint(angleToEncoderUnits(angle));
        components.getAngularController().enable();
    }

    public void updateAngularMoveToAngle(double angle) {
        angle = getValidAngle(angle);
        components.getAngularController().update(angleToEncoderUnits(angle));
    }

    public double distanceToEncoderUnits(double distance) { //TODO Change and add angle
        double encoderUnitsTarget;
        if (distance > MIDDLE_DISTANCE) {
            encoderUnitsTarget = ShooterConstants.ShooterCalculation.SHOOTER_FORMULA_FAR(distance);
        } else {
            encoderUnitsTarget = ShooterConstants.ShooterCalculation.SHOOTER_FORMULA_CLOSE(distance);
        }
        return Math.min(encoderUnitsTarget, SHOOTER_MOTOR_MAX_VELOCITY);
    }

    public double RPMToEncoderUnitsInDecisecond(double RPM) {
        return (RPM * ENCODER_UNITS_PER_ROTATION) / MILLISECOND_TO_MINUTE;
    }

    public double encoderUnitsInDecisecondToRPM(double encoderUnits) {
        return (encoderUnits * MILLISECOND_TO_MINUTE) / ENCODER_UNITS_PER_ROTATION;
    }

    public double angleToEncoderUnits(double angle) {
        return ((angle / ANGLE_PER_ROTATION) * ENCODER_UNITS_PER_ROTATION) / ANGULAR_MOTOR_CONVERSION;
    }

    public double encoderUnitsToAngle(double encoderUnits) {
        return ((encoderUnits / ENCODER_UNITS_PER_ROTATION) * ANGLE_PER_ROTATION) / ANGULAR_MOTOR_CONVERSION;
    }

    public double getValidAngle(double angle) {
        return Math.min(MAX_POSSIBLE_ANGLE, Math.max(angle, MIN_POSSIBLE_ANGLE));
    }

    public void initIsBallShot() {
        lastRPMError = Integer.MAX_VALUE;
    }

    public boolean updateIsBallShot() {
        boolean isBallShot = false;
        if (components.getShooterController().getCurrentError() >
                MIN_ERROR_RPM && components.getShooterController().getCurrentError() > lastRPMError) {
            isBallShot = true;
        }
        lastRPMError = components.getShooterController().getCurrentError();
        return isBallShot;
    }

    public boolean isShooterMotorOnTarget() {
        return components.getShooterController().isOnTarget(RPMToEncoderUnitsInDecisecond(SHOOTER_TOLERANCE_RPM));
    }

    public boolean isAngularMotorOnTarget(){
        return components.getAngularController().isOnTarget(angleToEncoderUnits(ANGULAR_TOLERANCE_DEGREE));
    }
}

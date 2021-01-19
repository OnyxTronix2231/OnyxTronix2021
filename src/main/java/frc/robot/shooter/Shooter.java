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
    private NetworkTableEntry shooterKp;
    private NetworkTableEntry shooterKi;
    private NetworkTableEntry shooterKd;
    private NetworkTableEntry shooterKf;

    private NetworkTableEntry angleKp;
    private NetworkTableEntry angleKi;
    private NetworkTableEntry angleKd;
    private NetworkTableEntry angleKf;

    public Shooter(ShooterComponents components) {
        this.components = components;
        lastRPMError = Integer.MAX_VALUE;
        Shuffleboard.getTab("Shooter").addNumber("PID Error",
                () -> components.getMasterMotor().getClosedLoopError());
        Shuffleboard.getTab("Shooter").addNumber("Current Shooter Motor RPM",
                () -> encoderUnitsInMillisecondToRPM(components.getMasterMotor().getSelectedSensorVelocity()));
        Shuffleboard.getTab("Shooter").addNumber("Current velocity",
                () -> components.getAngleMotor().getSelectedSensorVelocity());
        Shuffleboard.getTab("Shooter").addNumber("current angle position", () ->
                encoderUnitsToAngle(components.getAngleMotor().getSelectedSensorPosition()));

        shooterKp = Shuffleboard.getTab("Shooter").add("shooterKp",
                components.getCtrePIDController().getPIDFTerms().getKp()).getEntry();
        shooterKi = Shuffleboard.getTab("Shooter").add("shooterKi",
                components.getCtrePIDController().getPIDFTerms().getKi()).getEntry();
        shooterKd = Shuffleboard.getTab("Shooter").add("shooterKd",
                components.getCtrePIDController().getPIDFTerms().getKd()).getEntry();
        shooterKf = Shuffleboard.getTab("Shooter").add("shooterKf",
                components.getCtrePIDController().getPIDFTerms().getKf()).getEntry();
        angleKp = Shuffleboard.getTab("Shooter").add("angleKp",
                components.getCtreMotionMagicController().getPIDFTerms().getKp()).getEntry();
        angleKi = Shuffleboard.getTab("Shooter").add("angleKi",
                components.getCtreMotionMagicController().getPIDFTerms().getKi()).getEntry();
        angleKd = Shuffleboard.getTab("Shooter").add("angleKd",
                components.getCtreMotionMagicController().getPIDFTerms().getKd()).getEntry();
        angleKf = Shuffleboard.getTab("Shooter").add("angleKf",
                components.getCtreMotionMagicController().getPIDFTerms().getKf()).getEntry();
    }

    @Override
    public void periodic() {
        components.getCtrePIDController().setPIDFTerms(shooterKp.getDouble
                        (components.getCtrePIDController().getPIDFTerms().getKp()),
                shooterKi.getDouble(components.getCtrePIDController().getPIDFTerms().getKi()),
                shooterKd.getDouble(components.getCtrePIDController().getPIDFTerms().getKd()),
                shooterKf.getDouble(components.getCtrePIDController().getPIDFTerms().getKf()));
        components.getCtreMotionMagicController().setPIDFTerms(shooterKp.getDouble
                        (components.getCtreMotionMagicController().getPIDFTerms().getKp()),
                shooterKi.getDouble(components.getCtreMotionMagicController().getPIDFTerms().getKi()),
                shooterKd.getDouble(components.getCtreMotionMagicController().getPIDFTerms().getKd()),
                shooterKf.getDouble(components.getCtreMotionMagicController().getPIDFTerms().getKf()));
    }

    public void moveShooterBySpeed(double speed) {
        components.getMasterMotor().set(speed);
    }

    public void stopShooterMotor() {
        moveShooterBySpeed(0);
    }

    public void setShooterRPM(double RPM) {
        components.getCtrePIDController().update(RPMToEncoderUnitsInMillisecond(RPM));
        components.getCtrePIDController().enable();
    }

    public void changeAngleBySpeed(double speed) {
        components.getAngleMotor().set(ControlMode.PercentOutput, speed);
    }

    public void stopAngleMotor() {
        changeAngleBySpeed(0);
    }

    public void initMoveToAngle(double angle) {
        angle = checkAngle(angle);
        components.getCtreMotionMagicController().setSetpoint(angleToEncoderUnits(angle));
        components.getCtreMotionMagicController().enable();
    }

    public void updateMoveToAngle(double angle) {
        angle = checkAngle(angle);
        components.getCtreMotionMagicController().update(angleToEncoderUnits(angle));
    }

    public double checkAngle(double angle) {
        return Math.min(MAX_ANGLE, Math.max(angle, MIN_ANGLE));
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

    public double RPMToEncoderUnitsInMillisecond(double RPM) {
        return (RPM * ENCODER_UNITS_PER_ROTATION) / MILLISECOND_TO_MINUTE;
    }

    public double encoderUnitsInMillisecondToRPM(double encoderUnits) {
        return (encoderUnits * MILLISECOND_TO_MINUTE) / ENCODER_UNITS_PER_ROTATION;
    }

    public double angleToEncoderUnits(double angle) {
        return ((angle / ANGLE_PER_ROTATION) * ENCODER_UNITS_PER_ROTATION) / ANGLE_MOTOR_CONVERSION;
    }

    public double encoderUnitsToAngle(double encoderUnits) {
        return ((encoderUnits / ENCODER_UNITS_PER_ROTATION) * ANGLE_PER_ROTATION) / ANGLE_MOTOR_CONVERSION;
    }

    public void startChecking() {
        lastRPMError = Integer.MAX_VALUE;
    }

    public boolean isBallShot() {
        boolean isBallShot = false;
        if (components.getCtrePIDController().getCurrentError() >
                MIN_ERROR_RPM && components.getCtrePIDController().getCurrentError() > lastRPMError) {
            isBallShot = true;
        }
        lastRPMError = components.getCtrePIDController().getCurrentError();
        return isBallShot;
    }

    public boolean isShooterMotorOnTarget() {
        return Math.abs(components.getCtrePIDController().getCurrentError()) < RPMToEncoderUnitsInMillisecond(SHOOTING_TOLERANCE);
    }

    public boolean angleMotorOnTarget(){
        return Math.abs(components.getCtreMotionMagicController().getCurrentError()) < angleToEncoderUnits(ANGLE_TOLERANCE);
    }
}
package frc.robot.shooter;

import static frc.robot.shooter.ShooterConstants.*;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.SHOOTER_MOTOR_MAX_VELOCITY;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    private final ShooterComponents components;
    private double lastRPMError;

    public Shooter(final ShooterComponents components) {
        this.components = components;
        lastRPMError = Integer.MAX_VALUE;
        Shuffleboard.getTab("Shooter").addNumber("PID Error",
                () -> components.getMasterMotor().getClosedLoopError());
        Shuffleboard.getTab("Shooter").addNumber("Current Shooter Motor RPM",
                () -> encoderUnitsToRPM(components.getMasterMotor().getSelectedSensorVelocity()));
        Shuffleboard.getTab("Shooter").addNumber("Current velocity",
                () -> components.getMasterMotor().getSelectedSensorVelocity());
        Shuffleboard.getTab("Shooter").addNumber("current angle Velocity", () ->
                components.getAngleMotor().getSelectedSensorVelocity());
        Shuffleboard.getTab("Shooter").addNumber("current angle RPM", () ->
                encoderUnitsToRPM(components.getAngleMotor().getSelectedSensorVelocity()));
    }

    public void shootBySpeed(final double speed) {
        components.getMasterMotor().set(speed);
    }

    public void changeAngleBySpeed(final double speed) {
        components.getAngleMotor().set(ControlMode.PercentOutput, speed);
    }

    public void stopMotor() {
        shootBySpeed(0);
    }

    public void stopAngleMotor() {
        changeAngleBySpeed(0);
    }

    public void changeAngleByPosition(double angle) {
        components.getCtreMotionMagicController().update(angle);
        components.getCtreMotionMagicController().enable();
    }

    public void setRPM(final double RPM) {
        components.getCtrePIDController().update(RPM);
        components.getCtrePIDController().enable();
    }

    public double distanceToEncoderUnits(double distance) { //TODO Change and add angle
        double encoderUnitsTarget;
        if (distance > MIDDLE_DISTANCE) {
            encoderUnitsTarget = ShooterConstants.ShooterCalculation.SHOOTER_FORMULA1(distance);
        } else {
            encoderUnitsTarget = ShooterConstants.ShooterCalculation.SHOOTER_FORMULA2(distance);
        }
        if (encoderUnitsTarget <= SHOOTER_MOTOR_MAX_VELOCITY){
            return encoderUnitsTarget;
        }
        return SHOOTER_MOTOR_MAX_VELOCITY;
    }

    public double RPMToEncoderUnits(double RPM) {
        return (RPM * ENCODER_UNITS_PER_ROTATION) / MILLISECOND_TO_MINUTE;
    }

    public double encoderUnitsToRPM(double encoderUnits) {
        return (encoderUnits * MILLISECOND_TO_MINUTE) / ENCODER_UNITS_PER_ROTATION;
    }

    public double angleToEncoderUnits(final double angle) {
        return (angle / ANGLE_TO_ENCODER_UNITS) * ENCODER_UNITS_PER_ROTATION;
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

    public boolean isReadyToShoot() {
        return components.getCtrePIDController().getCurrentError() < RPMToEncoderUnits(AT_SHOOTING_RPM);
    }
}

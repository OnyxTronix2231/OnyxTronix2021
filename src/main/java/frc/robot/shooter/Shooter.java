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
    NetworkTableEntry shooterKp;
    NetworkTableEntry shooterKi;
    NetworkTableEntry shooterKd;
    NetworkTableEntry shooterKf;

    NetworkTableEntry angleKp;
    NetworkTableEntry angleKi;
    NetworkTableEntry angleKd;
    NetworkTableEntry angleKf;

    public Shooter(ShooterComponents components) {
        this.components = components;
        lastRPMError = Integer.MAX_VALUE;
        Shuffleboard.getTab("Shooter").addNumber("PID Error",
                () -> components.getMasterMotor().getClosedLoopError());
        Shuffleboard.getTab("Shooter").addNumber("Current Shooter Motor RPM",
                () -> encoderUnitsToRPM(components.getMasterMotor().getSelectedSensorVelocity()));
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

    public void changeAngleBySpeed(double speed) {
        components.getAngleMotor().set(ControlMode.PercentOutput, speed);
    }

    public void stopShooterMotor() {
        moveShooterBySpeed(0);
    }

    public void stopAngleMotor() {
        changeAngleBySpeed(0);
    }

    public double checkAngle(double angle) {
        if (angle > MAX_ANGLE) {
            return MAX_ANGLE;
        }
        if (angle < MIN_ANGLE) {
            return MIN_ANGLE;
        }
        return angle;
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


    public void setRPM(double RPM) {
        components.getCtrePIDController().update(RPMToEncoderUnits(RPM));
        components.getCtrePIDController().enable();
    }

    public boolean angleMotorOnTarget(){
        return Math.abs(components.getCtreMotionMagicController().getCurrentError()) < angleToEncoderUnits(TOLERANCE);
    }


    public double distanceToEncoderUnits(double distance) { //TODO Change and add angle
        double encoderUnitsTarget;
        if (distance > MIDDLE_DISTANCE) {
            encoderUnitsTarget = ShooterConstants.ShooterCalculation.SHOOTER_FORMULA_FAR(distance);
        } else {
            encoderUnitsTarget = ShooterConstants.ShooterCalculation.SHOOTER_FORMULA_CLOSE(distance);
        }
        if (encoderUnitsTarget <= SHOOTER_MOTOR_MAX_VELOCITY) {
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

    public double angleToEncoderUnits(double angle) {
        return (angle / ANGLE_PER_ROTATION) * ENCODER_UNITS_PER_ROTATION;
    }

    public double encoderUnitsToAngle(double encoderUnits) {
        return (encoderUnits / ENCODER_UNITS_PER_ROTATION) * ANGLE_PER_ROTATION;
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

package frc.robot.turret;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.turret.TurretConstants.*;

public class Turret extends SubsystemBase {

    protected final TurretComponents components;
    private double startingAngle;
    private double targetAngle;
    private final NetworkTableEntry kP;
    private final NetworkTableEntry kI;
    private final NetworkTableEntry kD;
    private final NetworkTableEntry kF;
//    private final NetworkTableEntry cruiseVelocity;
//    private final NetworkTableEntry acceleration;
//    private final NetworkTableEntry accelerationSmoothing;

    public Turret(TurretComponents turretComponents) {
        this.components = turretComponents;
        components.getMotor().configForwardSoftLimitEnable(true);
        components.getMotor().configForwardSoftLimitThreshold(angleToEncoderUnits(MAX_DEGREE));
        components.getMotor().configReverseSoftLimitEnable(true);
        components.getMotor().configReverseSoftLimitThreshold(angleToEncoderUnits(MIN_DEGREE));
//        Shuffleboard.getTab("Turret").addNumber("Current velocity",
//                () -> components.getEncoder().getRate());
           Shuffleboard.getTab("Turret").addNumber("current angle",
                    this::getAngleRTR);
//        Shuffleboard.getTab("Turret").addNumber("current position ENC",
//                ()-> components.getEncoder().getCount());
//        Shuffleboard.getTab("Turret").addNumber("current ERROR ENC",
//                ()-> components.getMotor().getClosedLoopError());
           Shuffleboard.getTab("Turret").addNumber("current ERROR angle",
                   ()-> encoderUnitsToAngle(components.getMotor().getClosedLoopError()));
//
            kP = Shuffleboard.getTab("Turret").add("kP",
                    components.getTurretController().getPIDFTerms().getKp()).getEntry();
            kI = Shuffleboard.getTab("Turret").add("kI",
                    components.getTurretController().getPIDFTerms().getKi()).getEntry();
            kD = Shuffleboard.getTab("Turret").add("kD",
                    components.getTurretController().getPIDFTerms().getKd()).getEntry();
            kF = Shuffleboard.getTab("Turret").add("kF",
                components.getTurretController().getPIDFTerms().getKf()).getEntry();
//
//        cruiseVelocity = Shuffleboard.getTab("Turret").add("Cruise velocity",
//                components.getTurretController().getCruiseVelocity()).getEntry();
//        acceleration = Shuffleboard.getTab("Turret").add("Acceleration",
//                components.getTurretController().getAcceleration()).getEntry();
//        accelerationSmoothing = Shuffleboard.getTab("Turret" +
//                "").add("Acceleration smoothing",
//                components.getTurretController().getAccelerationSmoothing()).getEntry();
    }

    @Override
    public void periodic() {
            components.getTurretController().setPIDFTerms(
                   kP.getDouble(components.getTurretController().getPIDFTerms().getKp()),
                   kI.getDouble(components.getTurretController().getPIDFTerms().getKi()),
                   kD.getDouble(components.getTurretController().getPIDFTerms().getKd()),
                   kF.getDouble(components.getTurretController().getPIDFTerms().getKf()));
//        components.getTurretController().setCruiseVelocity((int)
//                cruiseVelocity.getDouble(components.getTurretController().getCruiseVelocity()));
//        components.getTurretController().setAcceleration((int)
//                acceleration.getDouble(components.getTurretController().getAcceleration()));
//        components.getTurretController().setAccelerationSmoothing((int)
//                accelerationSmoothing.getDouble(components.getTurretController().getAccelerationSmoothing()));
    }

    public void moveBySpeed(double speed) {
        components.getMotor().set(speed);
    }

    public void stop() {
        moveBySpeed(0);
        components.getTurretController().disable();
    }

    public void initMoveToAngle(double angle) {
        angle = getAngleByLimits(angle);
        components.getTurretController().setSetpoint(angleToEncoderUnits(angle));
        components.getTurretController().enable();
    }

    public void updateMoveToAngle(double angle) {
        angle = getAngleByLimits(angle);
        components.getTurretController().update(angleToEncoderUnits(angle));
    }

    public void initMoveByAngle(double angle) {
        startingAngle = getAngleRTR();
        targetAngle = angle;
        initMoveToAngle(startingAngle + angle);
    }

    public void updateMoveByAngle(double angle) {
        if (targetAngle != angle) {
            startingAngle = getAngleRTR();
            targetAngle = angle;
        }
        updateMoveToAngle(startingAngle + angle);
    }

    public double getAngleRTR() {
        return encoderUnitsToAngle(components.getEncoder().getCount());
    }

    public double encoderUnitsToAngle(double encoderUnits) {
        return (encoderUnits  * DEGREES_IN_CIRCLE) / ENCODER_UNITS_PER_ROUND;
    }

    public double angleToEncoderUnits(double angle) {
        return (ENCODER_UNITS_PER_ROUND / DEGREES_IN_CIRCLE) * angle;
    }

    public double getAngleByLimits(double angle) {
        angle = angle % DEGREES_IN_CIRCLE;
        if (angle > MAX_DEGREE) {
            angle = (angle - FLIP_POINT);
        } else if (angle < MIN_DEGREE) {
            angle = (angle + FLIP_POINT);
        }
        return angle;
    }

    public boolean isOnTarget() {
        return components.getTurretController().isOnTarget(angleToEncoderUnits(TOLERANCE_DEGREE));
    }


}

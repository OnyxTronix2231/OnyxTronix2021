package frc.robot.ballTrigger;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.ballTrigger.BallTriggerConstants.*;
import static frc.robot.ballTrigger.BallTriggerConstants.BallTriggerConstantsA.TOLERANCE_IN_RPM;

public class BallTrigger extends SubsystemBase {

    private final BallTriggerComponents components;
//    private final NetworkTableEntry kpEntry;
//    private final NetworkTableEntry kiEntry;
//    private final NetworkTableEntry kdEntry;
//    private final NetworkTableEntry kfEntry;

    public BallTrigger(BallTriggerComponents components) {
        this.components = components;
//        Shuffleboard.getTab("Ball Trigger").addNumber("Current error in encoder units",
//                () -> components.getMasterMotor().getClosedLoopError());
//        Shuffleboard.getTab("Ball Trigger").addNumber("Current error in RPM",
//                () -> encoderUnitsInDecisecondToRPM(components.getMasterMotor().getClosedLoopError()));
//        Shuffleboard.getTab("Ball Trigger").addNumber("Current velocity in encoder units",
//                () -> components.getEncoder().getRate());
         Shuffleboard.getTab("Ball Trigger").addNumber("Current RPM",
                   () -> encoderUnitsInDecisecondToRPM(components.getMasterMotor().getSelectedSensorVelocity()));

//        kpEntry = Shuffleboard.getTab("Ball Trigger").add("kP",
//                components.getPIDController().getPIDFTerms().getKp()).getEntry();
//
//        kiEntry = Shuffleboard.getTab("Ball Trigger").add("kI",
//                components.getPIDController().getPIDFTerms().getKi()).getEntry();
//
//        kdEntry = Shuffleboard.getTab("Ball Trigger").add("kD",
//                components.getPIDController().getPIDFTerms().getKd()).getEntry();
//
//        kfEntry = Shuffleboard.getTab("Ball Trigger").add("kF",
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
        components.getMasterMotor().set(speed);
    }

    public void initMoveByRPM(double rpm) {
        components.getPIDController().setSetpoint(rpmToEncoderUnitsInDecisecond(rpm));
        components.getPIDController().enable();
    }

    public void updateMoveByRPM(double rpm) {
        components.getPIDController().update(rpmToEncoderUnitsInDecisecond(rpm));
    }

    public double rpmToEncoderUnitsInDecisecond(double rpm) {
        return (rpm * ENCODER_UNITS_PER_ROTATION) / DECISECOND_IN_MIN;
    }

    public double encoderUnitsInDecisecondToRPM(double encoderUnits) {
        return (encoderUnits * DECISECOND_IN_MIN) / ENCODER_UNITS_PER_ROTATION;
    }

    public void stop() {
        moveBySpeed(0);
        components.getPIDController().disable();
    }

    public boolean isOnTarget() {
        return components.getPIDController().isOnTarget(rpmToEncoderUnitsInDecisecond(TOLERANCE_IN_RPM));
    }

    public void openPiston() {
        components.getSolenoid().set(OPEN_PISTON);
    }

    public void closePiston() {
        components.getSolenoid().set(CLOSE_PISTON);
    }
}

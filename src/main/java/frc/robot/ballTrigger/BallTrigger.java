package frc.robot.ballTrigger;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.ballTrigger.BallTriggerConstants.*;

public class BallTrigger extends SubsystemBase {

    private final BallTriggerComponents components;
    private final NetworkTableEntry kP;
    private final NetworkTableEntry kI;
    private final NetworkTableEntry kD;
    private final NetworkTableEntry kF;

    public BallTrigger(BallTriggerComponents components) {
        this.components = components;
        Shuffleboard.getTab("Ball Trigger").addNumber("Current error",
                () -> components.getMotor().getClosedLoopError());
        Shuffleboard.getTab("Ball Trigger").addNumber("Current RPM",
                () -> encoderUnitsInDecisecondToRPM(components.getMotor().getSelectedSensorVelocity()));
        Shuffleboard.getTab("Ball Trigger").addNumber("Current velocity in encoder units",
                () -> components.getMotor().getSelectedSensorVelocity());

        kP = Shuffleboard.getTab("Ball Trigger").add("kP",
                components.getPIDController().getPIDFTerms().getKp()).getEntry();

        kI = Shuffleboard.getTab("Ball Trigger").add("kI",
                components.getPIDController().getPIDFTerms().getKp()).getEntry();

        kD = Shuffleboard.getTab("Ball Trigger").add("kD",
                components.getPIDController().getPIDFTerms().getKp()).getEntry();

        kF = Shuffleboard.getTab("Ball Trigger").add("kF",
                components.getPIDController().getPIDFTerms().getKp()).getEntry();
    }

    @Override
    public void periodic() {
        components.getPIDController().setPIDFTerms(
                kP.getDouble(components.getPIDController().getPIDFTerms().getKp()),
                kI.getDouble(components.getPIDController().getPIDFTerms().getKi()),
                kD.getDouble(components.getPIDController().getPIDFTerms().getKd()),
                kF.getDouble(components.getPIDController().getPIDFTerms().getKf()));
    }

    public void moveBySpeed(double speed) {
        components.getMotor().set(speed);
    }

    public void initMoveByRPM(double rpm) {
        components.getPIDController().setSetpoint(RPMToEncoderUnitsInDecisecond(rpm));
        components.getPIDController().enable();
    }

    public void updateMoveByRPM(double rpm) {
        components.getPIDController().update(RPMToEncoderUnitsInDecisecond(rpm));
    }

    public double RPMToEncoderUnitsInDecisecond(double rpm) {
        return (rpm * ENCODER_UNITS_PER_ROTATION) / DECISECOND_IN_MIN;
    }

    public double encoderUnitsInDecisecondToRPM(double encoderUnits) {
        return (encoderUnits * DECISECOND_IN_MIN) / ENCODER_UNITS_PER_ROTATION;
    }

    public void stop() {
        moveBySpeed(0);
        components.getPIDController().disable();
    }

    public void openPiston() {
        components.getSolenoid().set(OPEN_PISTON);
    }

    public void closePiston() {
        components.getSolenoid().set(CLOSE_PISTON);
    }
}

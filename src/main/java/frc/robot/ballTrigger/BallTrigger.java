package frc.robot.ballTrigger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.ballTrigger.BallTriggerConstants.ENCODER_UNITS_PER_ROTATION;
import static frc.robot.ballTrigger.BallTriggerConstants.SECONDS_IN_MIN;
import static frc.robot.ballTrigger.BallTriggerConstants.HUNDREDS_OF_MILISECS_IN_SEC;
import static frc.robot.ballTrigger.BallTriggerConstants.OPEN_PISTON;

public class BallTrigger extends SubsystemBase {

    private final BallTriggerComponents components;

    public BallTrigger(BallTriggerComponents components) {
        this.components = components;
    }

    public void moveBallTriggerBySpeed(double speed) {
        components.getMasterMotor().set(speed);
    }

    public void moveBallTriggerByRPM(double RPM) {
        components.getPIDController().setSetpoint(RPMToEncoderUnits(RPM));
        components.getPIDController().enable();
    }

    public void updateBallTriggerRPM(double RPM) {
        components.getPIDController().update(RPMToEncoderUnits(RPM));
    }

    public double RPMToEncoderUnits(double RPM) {
        return RPM * ENCODER_UNITS_PER_ROTATION / (SECONDS_IN_MIN * HUNDREDS_OF_MILISECS_IN_SEC);
    }

    public double encoderUnitsToRPM(double encoderUnits) {
        return (encoderUnits * (SECONDS_IN_MIN * HUNDREDS_OF_MILISECS_IN_SEC)) / ENCODER_UNITS_PER_ROTATION;
    }

    public void stop() {
        moveBallTriggerBySpeed(0);
    }

    public void openPistons() {
        components.getSolenoid().set(OPEN_PISTON);
    }

    public void closePistons() {
        components.getSolenoid().set(!OPEN_PISTON);
    }
}

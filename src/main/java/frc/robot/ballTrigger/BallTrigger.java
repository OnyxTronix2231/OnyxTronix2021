package frc.robot.ballTrigger;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.BatterySim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.ballTrigger.BallTriggerConstants.ENCODER_UNITS_PER_ROTATION;
import static frc.robot.ballTrigger.BallTriggerConstants.HUNDREDS_OF_MILISECS_IN_MIN;
import static frc.robot.ballTrigger.BallTriggerConstants.OPEN_PISTON;

public class BallTrigger extends SubsystemBase {

    private final BallTriggerComponents components;

    public BallTrigger(BallTriggerComponents components) {
        this.components = components;
    }

    public void moveBallTriggerBySpeed(double speed) {
        components.getMasterMotor().set(speed);
    }

    public void initBallTriggerByRPM(double RPM) {
        components.getPIDController().setSetpoint(RPMToEncoderUnits(RPM));
        components.getPIDController().enable();
    }

    public void updateBallTriggerRPM(double RPM) {
        components.getPIDController().update(RPMToEncoderUnits(RPM));
    }

    public double RPMToEncoderUnits(double RPM) {
        return RPM * ENCODER_UNITS_PER_ROTATION / HUNDREDS_OF_MILISECS_IN_MIN;
    }

    public double encoderUnitsToRPM(double encoderUnits) {
        return (encoderUnits * HUNDREDS_OF_MILISECS_IN_MIN) / ENCODER_UNITS_PER_ROTATION;
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

    @Override
    public void simulationPeriodic() {
        components.getFlyWheelSim().setInputVoltage(components.getMasterMotor().getMotorOutputPercent()
                * RobotController.getBatteryVoltage());
        components.getFlyWheelSim().update(0.02);
        components.getMasterMotor().getSimCollection().setQuadratureVelocity((int)
                RPMToEncoderUnits(components.getFlyWheelSim().getAngularVelocityRPM()));
        components.getMasterMotor().getSimCollection().setSupplyCurrent(components.getFlyWheelSim().getCurrentDrawAmps());
        RoboRioSim.setVInVoltage(BatterySim.calculateDefaultBatteryLoadedVoltage(
                components.getFlyWheelSim().getCurrentDrawAmps()));
    }
}

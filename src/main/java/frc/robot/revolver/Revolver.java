package frc.robot.revolver;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.BatterySim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.revolver.RevolverConstants.ENCODER_UNITS_PER_ROTATION;
import static frc.robot.revolver.RevolverConstants.HUNDREDS_OF_MILLISECS_IN_MIN;
import static frc.robot.revolver.RevolverConstants.TOLERANCE_IN_RPM;

public class Revolver extends SubsystemBase {

    private final RevolverComponents components;

    public Revolver(RevolverComponents components) {
        this.components = components;
    }

    public void moveRevolverBySpeed(double speed) {
        components.getMasterMotor().set(speed);
    }

    public void initRevolverByRPM(double RPM) {
        components.getPIDController().setSetpoint(RPMToEncoderUnit(RPM));
        components.getPIDController().enable();
    }

    public void updateRevolverRPM(double RPM) {
        components.getPIDController().update(RPMToEncoderUnit(RPM));
    }

    public void stop() {
        moveRevolverBySpeed(0);
    }

    public boolean isOnTarget() {
        return components.getPIDController().isOnTarget(TOLERANCE_IN_RPM);
    }

    public double RPMToEncoderUnit(double RPM) {
        return RPM * ENCODER_UNITS_PER_ROTATION / HUNDREDS_OF_MILLISECS_IN_MIN;
    }

    public double encoderUnitsToRPM(double encoderUnits) {
        return (encoderUnits * HUNDREDS_OF_MILLISECS_IN_MIN) / ENCODER_UNITS_PER_ROTATION;
    }

    @Override
    public void simulationPeriodic() {
        components.getFlyWheelSim().setInputVoltage(components.getMasterMotor().getMotorOutputPercent()
                * RobotController.getBatteryVoltage());
        components.getFlyWheelSim().update(0.02);
        components.getMasterMotor().getSimCollection().setQuadratureVelocity((int)
                RPMToEncoderUnit(components.getFlyWheelSim().getAngularVelocityRPM()));
        components.getMasterMotor().getSimCollection().setSupplyCurrent(components.getFlyWheelSim().getCurrentDrawAmps());
        RoboRioSim.setVInVoltage(BatterySim.calculateDefaultBatteryLoadedVoltage(
                components.getFlyWheelSim().getCurrentDrawAmps()));
    }
}

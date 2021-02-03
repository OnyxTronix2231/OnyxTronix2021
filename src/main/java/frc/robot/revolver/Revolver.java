package frc.robot.revolver;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.BatterySim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.revolver.RevolverConstants.ENCODER_UNITS_PER_ROTATION;
import static frc.robot.revolver.RevolverConstants.DECISECOND_IN_MIN;
import static frc.robot.revolver.RevolverConstants.TOLERANCE_IN_RPM;

public class Revolver extends SubsystemBase {

    private final RevolverComponents components;
    private final NetworkTableEntry kP;
    private final NetworkTableEntry kI;
    private final NetworkTableEntry kD;
    private final NetworkTableEntry kF;

    public Revolver(RevolverComponents components) {
        this.components = components;
        Shuffleboard.getTab("Revolver").addNumber("Current error",
                () -> encoderUnitsInDecisecondToRPM(components.getMasterMotor().getClosedLoopError()));
        Shuffleboard.getTab("Revolver").addNumber("Current RPM",
                () -> encoderUnitsInDecisecondToRPM(components.getMasterMotor().getSelectedSensorVelocity()));
        Shuffleboard.getTab("Revolver").addNumber("Current velocity in encoder units",
                () -> components.getMasterMotor().getSelectedSensorVelocity());

        kP = Shuffleboard.getTab("Revolver").add("kP",
                components.getPIDController().getPIDFTerms().getKp()).getEntry();

        kI = Shuffleboard.getTab("Revolver").add("kI",
                components.getPIDController().getPIDFTerms().getKp()).getEntry();

        kD = Shuffleboard.getTab("Revolver").add("kD",
                components.getPIDController().getPIDFTerms().getKp()).getEntry();

        kF = Shuffleboard.getTab("Revolver").add("kF",
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
        components.getMasterMotor().set(speed);
    }

    public void initMoveByRPM(double RPM) {
        components.getPIDController().setSetpoint(RPMToEncoderUnitInDecisecond(RPM));
        components.getPIDController().enable();
    }

    public void updateMoveByRPM(double RPM) {
        components.getPIDController().update(RPMToEncoderUnitInDecisecond(RPM));
    }

    public void stop() {
        moveBySpeed(0);
        components.getPIDController().disable();
    }

    public boolean isOnTarget() {
        return components.getPIDController().isOnTarget(RPMToEncoderUnitInDecisecond(TOLERANCE_IN_RPM));
    }

    public double RPMToEncoderUnitInDecisecond(double RPM) {
        return RPM * ENCODER_UNITS_PER_ROTATION / DECISECOND_IN_MIN;
    }

    public double encoderUnitsInDecisecondToRPM(double encoderUnits) {
        return (encoderUnits * DECISECOND_IN_MIN) / ENCODER_UNITS_PER_ROTATION;
    }

    @Override
    public void simulationPeriodic() {
        components.getLinearSystemSim().setInput(components.getMasterMotor().getMotorOutputPercent()
                * RobotController.getBatteryVoltage());
        components.getLinearSystemSim().update(0.02);
        components.getMasterMotor().getSimCollection().setQuadratureVelocity((int)
                RPMToEncoderUnitInDecisecond(components.getLinearSystemSim().getOutput(0)));
        components.getMasterMotor().getSimCollection().setSupplyCurrent(components.getLinearSystemSim().getCurrentDrawAmps());
        RoboRioSim.setVInVoltage(BatterySim.calculateDefaultBatteryLoadedVoltage(
                components.getLinearSystemSim().getCurrentDrawAmps()));
    }
}
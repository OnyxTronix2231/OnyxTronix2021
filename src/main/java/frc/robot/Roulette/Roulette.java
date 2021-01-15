package frc.robot.Roulette;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

import static frc.robot.Roulette.RouletteConstants.*;

public class Roulette extends SubsystemBase {

    private final RouletteComponents components;

    public Roulette(RouletteComponents components) {
        this.components = components;
    }

    public void openPiston() {
        components.getSolenoid().set(true);
    }

    public void closePiston() {
        components.getSolenoid().set(false);
    }

    public void setSpeed(double speed) {
        components.getMasterMotor().set(speed);
    }

    public RouletteColor colorMatching(RouletteColor color) {
        Optional<RouletteColor> closestColor = Arrays.stream(ROULETTE_COLORS).
                max(Comparator.comparing(c -> c.howCloseTo(color)));
        return closestColor.orElse(null);
    }

    public void initMoveByRotation(double rotations) {
        reset();
        components.getController().setSetpoint(wheelRotationToEncoderUnits(rotations));
        components.getController().enable();
    }

    public boolean isOnTarget() {
        return components.getController().isOnTarget(PERCENT_TOLERANCE
                * wheelRotationToEncoderUnits(1));
    }

    public void updateMoveByRotations(double rotations) {
        this.components.getController().update(wheelRotationToEncoderUnits(rotations));
    }

    public double wheelRotationToEncoderUnits(double rotations) {
        return rotations * ENCODER_UNITS_PER_ROUND ;
    }

    public double encoderUnitsToRotation(double encoderUnits) {
        return encoderUnits / ENCODER_UNITS_PER_ROUND / RATIO_ROULETTE_TO_WEEL;
    }

    public void reset() {
        components.getEncoder().reset();
    }

    public void stop() {
        components.getMasterMotor().set(0);
    }

}

package frc.robot.Roulette;

import edu.wpi.first.wpilibj.DriverStation;
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

    public void updateMoveByRotations(double wheelRotations) {
        this.components.getController().update(wheelRotationToEncoderUnits(wheelRotations));
    }

    public boolean isOnTarget() {
        return components.getController().isOnTarget(PERCENT_TOLERANCE
                * wheelRotationToEncoderUnits(1));
    }

    public double encoderUnitsToRouletteRounds() {
        return encoderUnitsToRotation(components.getMasterMotor().
                getSelectedSensorPosition()) * (1 / RATIO_ROULETTE_TO_WHEEL);
    }

    public RouletteColor getGameRequiredColor() {
        String gameData = DriverStation.getInstance().getGameSpecificMessage();
        if (gameData.isBlank()) {
            return null;
        }
        switch (gameData.charAt(0)) {
            case 'B':
                return ROULETTE_BLUE;
            case 'R':
                return ROULETTE_RED;
            case 'G':
                return ROULETTE_GREEN;
            case 'Y':
                return ROULETTE_YELLOW;
            default:
                return null;
        }
    }

    public RouletteColor getCurrentColor() {
        return colorMatching(new RouletteColor(components.getColorSensor().getColor()));
    }

    public double getRoundsToColor(RouletteColor requiredColor) {
        RouletteColor currentColor = getCurrentColor();
        int requiredColorIndex = Arrays.asList(ROULETTE_COLORS).indexOf(requiredColor);
        int currentColorIndex = Arrays.asList(ROULETTE_COLORS).indexOf(currentColor);
        switch (Math.abs(requiredColorIndex - currentColorIndex)) {
            case 0:
                return (4 * ROULETTE_COLOR_DISTANCE) / WHEEL_CIRCUMFERENCE;
            case 1:
            case 3:
                return ROULETTE_COLOR_DISTANCE / WHEEL_CIRCUMFERENCE;
            case 2:
                return (2 * ROULETTE_COLOR_DISTANCE) / WHEEL_CIRCUMFERENCE;
            default:
                return 0;
        }
    }

    public void getTurningDirection(RouletteColor requiredColor) {
        RouletteColor currentColor = getCurrentColor();
        int requiredColorIndex = Arrays.asList(ROULETTE_COLORS).indexOf(requiredColor);
        int currentColorIndex = Arrays.asList(ROULETTE_COLORS).indexOf(currentColor);
        if (requiredColorIndex - currentColorIndex <= 0) {
            components.getMasterMotor().setInverted(right);//?
        } else {
            if (requiredColorIndex - currentColorIndex > 0)
                components.getMasterMotor().setInverted(left);//?
        }
    }

    public double wheelRotationToEncoderUnits(double rotations) {
        return rotations * ENCODER_UNITS_PER_ROUND;
    }

    public double encoderUnitsToRotation(double encoderUnits) {
        return encoderUnits / ENCODER_UNITS_PER_ROUND;
    }

    public void reset() {
        components.getEncoder().reset();
    }

    public void stop() {
        components.getMasterMotor().set(0);
    }

}

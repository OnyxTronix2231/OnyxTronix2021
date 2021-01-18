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

    public void initMoveByRotation(double rouletteRounds) {
        reset();
        components.getController().setSetpoint(rouletteRoundsToEncoderUnits(rouletteRounds));
        components.getController().enable();
    }

    public void updateMoveByRotations(double rouletteRounds) {
        this.components.getController().update(rouletteRoundsToEncoderUnits(rouletteRounds));
    }

    public boolean isOnTarget() {
        return components.getController().isOnTarget(PERCENT_TOLERANCE
                * rouletteRoundsToEncoderUnits(1));
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

    public double getRoundsToColor(RouletteColor requiredColor) {//
        RouletteColor currentColor = getCurrentColor();
        int requiredColorIndex = Arrays.asList(ROULETTE_COLORS).indexOf(requiredColor);
        int currentColorIndex = Arrays.asList(ROULETTE_COLORS).indexOf(currentColor);
        double distance = (requiredColorIndex - currentColorIndex);
        if (distance == 3) {
            distance = -1;
        } else if (distance == -3) {
            distance = 1;
        }
        return distance * RATIO_ROULETTE_TO_ROULETTE_COLOR;
    }

    public double rouletteRoundsToEncoderUnits(double rouletteRounds) {
        return rouletteRounds * ENCODER_UNITS_PER_WHEEL_ROUND * RATIO_ROULETTE_TO_WHEEL;
    }

    public double encoderUnitsToRouletteRounds(double encoderUnits) {
        return encoderUnits / ENCODER_UNITS_PER_WHEEL_ROUND / RATIO_ROULETTE_TO_WHEEL;
    }

    public void reset() {
        components.getEncoder().reset();
    }

    public void stop() {
        components.getMasterMotor().set(0);
    }

}

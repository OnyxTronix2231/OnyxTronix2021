package frc.robot.Roulette;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.simulation.BatterySim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

import static frc.robot.Roulette.RouletteConstants.*;

public class Roulette extends SubsystemBase {

    private final RouletteComponents components;
    private final NetworkTableEntry shuffleboardEntryKP;
    private final NetworkTableEntry shuffleboardEntryKI;
    private final NetworkTableEntry shuffleboardEntryKD;
    private final NetworkTableEntry shuffleboardEntryKF;
    private final NetworkTableEntry shuffleboardEntryCruiseVelocity;
    private final NetworkTableEntry shuffleboardEntryAcceleration;
    //private final NetworkTableEntry shuffleboardEntryAccelerationSmoothing;

    public Roulette(RouletteComponents components) {
        this.components = components;
        Shuffleboard.getTab("Roulette").addNumber("Roulette rotations complete"
                , this::getCurrentRouletteRotations);
        //Shuffleboard.getTab("Roulette").addString("Current roulette color", () ->
          //      getCurrentColor().getName());
        shuffleboardEntryKP = Shuffleboard.getTab("Roulette").add("kP",
                components.getController().getPIDFTerms().getKp()).getEntry();
        shuffleboardEntryKI = Shuffleboard.getTab("Roulette").add("kI",
                components.getController().getPIDFTerms().getKi()).getEntry();
        shuffleboardEntryKD = Shuffleboard.getTab("Roulette").add("kD",
                components.getController().getPIDFTerms().getKd()).getEntry();
        shuffleboardEntryKF = Shuffleboard.getTab("Roulette").add("kF",
                components.getController().getPIDFTerms().getKf()).getEntry();
        shuffleboardEntryCruiseVelocity = Shuffleboard.getTab("Roulette").add("entry",
                components.getController().getCruiseVelocity()).getEntry();
        shuffleboardEntryAcceleration = Shuffleboard.getTab("Roulette").add("velocity",
                components.getController().getAcceleration()).getEntry();
       // shuffleboardEntryAccelerationSmoothing = Shuffleboard.getTab("Roulette").add("entry",
         //       components.getController().getAccelerationSmoothing()).getEntry();
    }

    @Override
    public void periodic() {
        components.getController().setPIDFTerms(
                shuffleboardEntryKP.getDouble(components.getController().getPIDFTerms().getKp()),
                shuffleboardEntryKI.getDouble(components.getController().getPIDFTerms().getKi()),
                shuffleboardEntryKD.getDouble(components.getController().getPIDFTerms().getKd()),
                shuffleboardEntryKF.getDouble(components.getController().getPIDFTerms().getKf()));
        components.getController().setCruiseVelocity((int) shuffleboardEntryCruiseVelocity.
                getDouble(components.getController().getCruiseVelocity()));
        components.getController().setAcceleration((int) shuffleboardEntryAcceleration.
                getDouble(components.getController().getAcceleration()));
     //   components.getController().setAccelerationSmoothing((int) shuffleboardEntryAccelerationSmoothing.
     //           getDouble(components.getController().getAccelerationSmoothing()));
    }

    @Override
    public void simulationPeriodic() {
        components.getSimulator().setInput(components.getMasterMotor().getMotorOutputPercent()
                * RobotController.getBatteryVoltage());
        components.getSimulator().update(0.02);
        components.getMasterMotor().getSimCollection().setQuadratureRawPosition(
                (int) rouletteRoundsToEncoderUnits(components.getSimulator().getOutput(0)));
        components.getMasterMotor().getSimCollection().setSupplyCurrent(components.getSimulator()
                .getCurrentDrawAmps());
        RoboRioSim.setVInVoltage(BatterySim.calculateDefaultBatteryLoadedVoltage(
                components.getSimulator().getCurrentDrawAmps()));
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

    public double getCurrentRouletteRotations() {
        return encoderUnitsToRouletteRounds(components.getEncoder().getCount());
    }

    public RouletteColor colorMatching(Color color) {
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
        switch (gameData.charAt(MIN_COLOR_INDEX)) {
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
        return colorMatching(components.
                getColorSensor().getColor());
    }

    public double getRoundsToColor(RouletteColor requiredColor) {
        RouletteColor currentColor = getCurrentColor();
        int requiredColorIndex = Arrays.asList(ROULETTE_COLORS).indexOf(requiredColor);
        int currentColorIndex = Arrays.asList(ROULETTE_COLORS).indexOf(currentColor);
        if (currentColorIndex + COLOR_OFFSET > MAX_COLOR_INDEX) {
            currentColorIndex = currentColorIndex - COLOR_OFFSET;
        } else {
            currentColorIndex = currentColorIndex + COLOR_OFFSET;
        }
        int distance = (requiredColorIndex - currentColorIndex);
        if (distance == INEFFICIENT_DISTANCE) {
            distance = -OPTIMIZED_DISTANCE;
        } else if (distance == -INEFFICIENT_DISTANCE) {
            distance = OPTIMIZED_DISTANCE;
        }
        return colorCountToRouletteRounds(distance);
    }

    public double colorCountToRouletteRounds(int count) {
        return count * RATIO_ROULETTE_TO_ROULETTE_COLOR;
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
        components.getController().disable();
    }

}

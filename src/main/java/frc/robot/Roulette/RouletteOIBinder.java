package frc.robot.Roulette;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Roulette.commands.SpinByRouletteRounds;
import frc.robot.Roulette.commands.SpinBySpeed;
import frc.robot.Roulette.commands.SpinToColor;

import java.util.function.DoubleSupplier;

public class RouletteOIBinder {

    public RouletteOIBinder(Roulette roulette, Trigger spinToGameColor) {
       // spinToGameColor.whenActive(new SpinToColor(roulette, roulette::getGameRequiredColor));
        spinToGameColor.whileActiveContinuous(new SpinByRouletteRounds(roulette, () -> 3));
    }
}

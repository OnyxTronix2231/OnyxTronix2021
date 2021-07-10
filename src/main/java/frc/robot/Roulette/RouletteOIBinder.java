package frc.robot.Roulette;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Roulette.commands.SpinToColor;
//why does this class exist if never used?
public class RouletteOIBinder {

    public RouletteOIBinder(Roulette roulette, Trigger spinToGameColor) {
        spinToGameColor.whenActive(new SpinToColor(roulette, roulette::getGameRequiredColor));
    }
}

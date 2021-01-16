package frc.robot.Roulette.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Roulette.Roulette;

public class ClosePiston extends InstantCommand {

    public ClosePiston(Roulette roulette) {
        super(roulette::closePiston);
    }

    // how do you make sure the engine is off when the pistol is turned off


}

package frc.robot.Roulette.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Roulette.Roulette;
//why does this class exist if never used?
public class OpenPiston extends InstantCommand {

    public OpenPiston(Roulette roulette) {
        super(roulette::openPiston);
    }
}

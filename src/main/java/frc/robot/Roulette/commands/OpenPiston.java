package frc.robot.Roulette.commands;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Roulette.Roulette;

public class OpenPiston extends InstantCommand {

    public OpenPiston(Roulette roulette){
        super(roulette::openPiston);
    }

}

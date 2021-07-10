package frc.robot.vision.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.vision.BaseVision;

public class ActByVision extends ConditionalCommand {

//    public ActByVision(Command onTrue, Command onFalse, BaseVision vision) {
//        super(onTrue, onFalse, vision::hasTarget);
//    }

    public ActByVision(Command onTrue, BaseVision vision) {
        super(onTrue, new InstantCommand(), vision::hasTarget);
    }
}

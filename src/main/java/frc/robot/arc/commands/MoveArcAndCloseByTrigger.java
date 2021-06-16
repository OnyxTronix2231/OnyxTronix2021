package frc.robot.arc.commands;

import static frc.robot.arc.ArcConstants.MIN_POSSIBLE_ANGLE;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;

public class MoveArcAndCloseByTrigger {

    public MoveArcAndCloseByTrigger(Arc arc, Trigger moveArc, Command command){
        moveArc.whileActiveOnce(command);
        moveArc.whenInactive(new MoveArcToAngle(arc, () -> MIN_POSSIBLE_ANGLE));
    }
}

package frc.robot.arc.commands;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.arc.Arc;

import static frc.robot.arc.ArcConstants.MOVE_ARC_TO_SWITCH_LIMIT_SPEED;

public class CalibrateArc extends ConditionalCommand {

    public CalibrateArc(Arc arc) {
        super(new InstantCommand(),
                new MoveUntilLowerLimitSwitch(arc, () -> MOVE_ARC_TO_SWITCH_LIMIT_SPEED)
                        .andThen(new MoveUntilMechanismLimit(arc)),
                arc::hasHitLowerLimit);
    }
}

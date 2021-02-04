package frc.robot.arc.commands;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.arc.Arc;

public class CalibrateArc extends ConditionalCommand {

    public CalibrateArc(Arc arc) {
        super(new InstantCommand(),
                new MoveUntilLowerLimitSwitch(arc).andThen(new MoveUntilMechanismLimit(arc)),
                arc::hasHitReverseLimit);
    }
}

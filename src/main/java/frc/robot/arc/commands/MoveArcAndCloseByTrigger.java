package frc.robot.arc.commands;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;

import java.util.function.DoubleSupplier;

import static frc.robot.arc.ArcConstants.MIN_POSSIBLE_ANGLE;

public class MoveArcAndCloseByTrigger extends MoveArcToAngle {

    public MoveArcAndCloseByTrigger(Arc arc, Trigger closeArc, DoubleSupplier angleSupplier) {
        super(arc, angleSupplier);
        closeArc.whenInactive(new MoveArcToAngle(arc, () -> MIN_POSSIBLE_ANGLE));
    }

    public MoveArcAndCloseByTrigger(Arc arc, DoubleSupplier angleSupplier) {
        super(arc, angleSupplier);
    }
}

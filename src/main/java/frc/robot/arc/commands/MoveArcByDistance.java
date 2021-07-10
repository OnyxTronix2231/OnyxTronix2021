package frc.robot.arc.commands;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;

import java.util.function.DoubleSupplier;

public class MoveArcByDistance extends MoveArcAndCloseByTrigger {

    public MoveArcByDistance(Arc arc, Trigger closeArc, DoubleSupplier distanceSupplier) {
        super(arc, closeArc, () -> arc.distanceMetersToAngle(distanceSupplier.getAsDouble()));
    }

    public MoveArcByDistance(Arc arc, DoubleSupplier distanceSupplier) {
        super(arc, () -> arc.distanceMetersToAngle(distanceSupplier.getAsDouble()));
    }
}

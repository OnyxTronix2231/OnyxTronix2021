package frc.robot.arc.commands;

import frc.robot.arc.Arc;

import java.util.function.DoubleSupplier;

public class MoveArcByDistance extends ActivateCommandAndReturnToStart {

    public MoveArcByDistance(Arc arc, DoubleSupplier distanceSupplier) {
        super(arc, new MoveArcToAngle(arc, () -> arc.distanceMetersToAngle(distanceSupplier.getAsDouble())));
    }
}

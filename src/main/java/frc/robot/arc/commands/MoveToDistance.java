package frc.robot.arc.commands;

import frc.robot.arc.Arc;

import java.util.function.DoubleSupplier;

public class MoveToDistance extends MoveToAngle {

    public MoveToDistance(Arc arc, DoubleSupplier distanceSupplier) {
        super(arc, () -> arc.distanceMetersToAngle(distanceSupplier.getAsDouble()));
    }
}

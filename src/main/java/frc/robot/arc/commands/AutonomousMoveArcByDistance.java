package frc.robot.arc.commands;

import frc.robot.arc.Arc;

import java.util.function.DoubleSupplier;

public class AutonomousMoveArcByDistance extends MoveArcToAngle {

    public AutonomousMoveArcByDistance(Arc arc, DoubleSupplier distanceSupplier) {
        super(arc, () -> arc.distanceMetersToAngle(distanceSupplier.getAsDouble()));
    }
}

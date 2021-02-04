package frc.robot.arc.commands;

import frc.robot.arc.Arc;

import java.util.function.DoubleSupplier;

public class MoveByDistance extends MoveByAngle {

    public MoveByDistance(Arc arc, DoubleSupplier distanceSupplier) {
        super(arc, ()-> arc.distanceMeterToAngle(distanceSupplier.getAsDouble()) );
    }
}

package frc.robot.arc.commands;

import frc.robot.arc.Arc;

import java.util.function.DoubleSupplier;

public class MoveArcByDistance extends MoveArcByAngle{

    public MoveArcByDistance(Arc arc, DoubleSupplier distanceSupplier) {
        super(arc, ()-> arc.distanceMeterToAngle(distanceSupplier.getAsDouble()) );
    }
}

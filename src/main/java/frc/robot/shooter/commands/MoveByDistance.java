package frc.robot.shooter.commands;

import frc.robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class MoveByDistance extends MoveByRPM {

    public MoveByDistance(Shooter shooter, DoubleSupplier distanceSupplier) {
        super(shooter, () -> shooter.encoderUnitsInDecisecondToRPM(shooter.
                distanceMeterToEncoderUnitInDecisecond(distanceSupplier.getAsDouble())));
    }
}

package frc.robot.flywheel.commands;

import frc.robot.flywheel.Shooter;

import java.util.function.DoubleSupplier;

public class MoveShooterByDistance extends MoveShooterByRPM {

    public MoveShooterByDistance(Shooter shooter, DoubleSupplier distanceSupplier) {
        super(shooter, () -> shooter.encoderUnitsInDecisecondToRPM(shooter.distanceMeterToEncoderUnitInDecisecond(distanceSupplier.getAsDouble())));
    }
}

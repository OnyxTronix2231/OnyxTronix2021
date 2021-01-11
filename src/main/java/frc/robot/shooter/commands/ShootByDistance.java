package frc.robot.shooter.commands;

import frc.robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class ShootByDistance extends ShootByRPM {

    public ShootByDistance(final Shooter shooter, final DoubleSupplier distanceSupplier) {
        super(shooter, () -> shooter.encoderUnitsToRPM(shooter.distanceToEncoderUnits(distanceSupplier.getAsDouble())));
    }
}

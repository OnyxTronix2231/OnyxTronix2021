package frc.robot.shooter.commands;

import frc.robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class ShootByDistance extends ShootByRPM {

    public ShootByDistance(Shooter shooter, DoubleSupplier distanceSupplier) {
        super(shooter, () -> shooter.encoderUnitsInDecisecondToRPM(shooter.distanceToEncoderUnits(distanceSupplier.getAsDouble())));
    }
}

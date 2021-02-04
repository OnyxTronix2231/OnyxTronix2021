package frc.robot.shooter.commands;

import frc.robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class SpinShooterByDistance extends SpinShooterByRPM {

    public SpinShooterByDistance(Shooter shooter, DoubleSupplier distanceSupplier) {
        super(shooter, () -> shooter.encoderUnitsInDecisecondToRPM(shooter.
                distanceMetersToEncoderUnitsInDecisecond(distanceSupplier.getAsDouble())));
    }
}

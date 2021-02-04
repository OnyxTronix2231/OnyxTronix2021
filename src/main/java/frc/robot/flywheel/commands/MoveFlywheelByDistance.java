package frc.robot.flywheel.commands;

import frc.robot.flywheel.Flywheel;

import java.util.function.DoubleSupplier;

public class MoveFlywheelByDistance extends MoveFlywheelByRPM {

    public MoveFlywheelByDistance(Flywheel flywheel, DoubleSupplier distanceSupplier) {
        super(flywheel, () -> flywheel.encoderUnitsInDecisecondToRPM(
                flywheel.distanceMetersToEncoderUnitsInDecisecond(distanceSupplier.getAsDouble())));
    }
}

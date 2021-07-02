package frc.robot.arc.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;

import java.util.function.DoubleSupplier;

public class MoveArcByDistance extends CommandBase {

    public MoveArcByDistance(Arc arc, Trigger closeArc, DoubleSupplier distanceSupplier) {
        new MoveArcAndCloseByTrigger(arc, closeArc, () -> arc.distanceMetersToAngle(distanceSupplier.getAsDouble()));
    }

    public MoveArcByDistance(Arc arc, DoubleSupplier distanceSupplier) {
        new MoveArcToAngle(arc, () -> arc.distanceMetersToAngle(distanceSupplier.getAsDouble()));
    }
}

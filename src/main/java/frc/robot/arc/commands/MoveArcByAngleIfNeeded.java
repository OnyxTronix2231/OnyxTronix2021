package frc.robot.arc.commands;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.arc.Arc;

import java.util.function.DoubleSupplier;

public class MoveArcByAngleIfNeeded extends ConditionalCommand {

    public MoveArcByAngleIfNeeded(Arc arc, DoubleSupplier angle) {
        super(new InstantCommand(), new MoveArcToAngle(arc, angle), ()-> arc.isOnTargetByRealValue(angle.getAsDouble()));
    }
}

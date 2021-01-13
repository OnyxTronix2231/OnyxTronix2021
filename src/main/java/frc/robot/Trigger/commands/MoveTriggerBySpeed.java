package frc.robot.Trigger.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Trigger.Trigger;

import java.util.function.DoubleSupplier;

public class MoveTriggerBySpeed extends CommandBase {

    private final Trigger trigger;
    private final DoubleSupplier speedSupplier;

    public MoveTriggerBySpeed(Trigger trigger, DoubleSupplier speedSupplier) {
        this.trigger = trigger;
        this.speedSupplier = speedSupplier;
        addRequirements(trigger);
    }

    @Override
    public void execute() {
        trigger.moveBallTriggerBySpeed(speedSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        trigger.stop();
    }
}

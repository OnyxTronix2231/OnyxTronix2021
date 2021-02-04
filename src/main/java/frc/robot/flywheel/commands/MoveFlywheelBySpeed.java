package frc.robot.flywheel.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.flywheel.Flywheel;

import java.util.function.DoubleSupplier;

public class MoveFlywheelBySpeed extends CommandBase {

    private final Flywheel flywheel;
    private final DoubleSupplier speedSupplier;

    public MoveFlywheelBySpeed(Flywheel flywheel, DoubleSupplier speedSupplier) {
        this.flywheel = flywheel;
        this.speedSupplier = speedSupplier;
        addRequirements(flywheel);
    }

    @Override
    public void execute() {
        flywheel.moveBySpeed(speedSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        flywheel.stop();
    }
}

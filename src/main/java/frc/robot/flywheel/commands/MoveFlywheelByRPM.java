package frc.robot.flywheel.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.flywheel.Flywheel;

import java.util.function.DoubleSupplier;

public class MoveFlywheelByRPM extends CommandBase {

    private final Flywheel flywheel;
    private final DoubleSupplier RPMSupplier;

    public MoveFlywheelByRPM(Flywheel flywheel, DoubleSupplier RPMSupplier) {
        this.flywheel = flywheel;
        this.RPMSupplier = RPMSupplier;
        addRequirements(flywheel);
    }

    @Override
    public void initialize() {
        flywheel.initMoveByRPM(RPMSupplier.getAsDouble());
    }

    @Override
    public void execute() {
        flywheel.updateMoveByRPM(RPMSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        flywheel.stop();
    }
}

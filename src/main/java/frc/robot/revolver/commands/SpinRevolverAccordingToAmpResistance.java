package frc.robot.revolver.commands;

import static frc.robot.revolver.RevolverConstants.UNCLOG_SPEED;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import frc.robot.revolver.Revolver;

import java.util.function.DoubleSupplier;

public class SpinRevolverAccordingToAmpResistance extends ConditionalCommand {

    public SpinRevolverAccordingToAmpResistance(Revolver revolver, Command command) {
        super(new UnclogRevolver(revolver, () ->UNCLOG_SPEED), command,
            revolver::isStuck);
    }

    @Override
    public boolean isFinished() {
        if (super.isFinished()) {
            initialize();
        }
        return false;
    }
}

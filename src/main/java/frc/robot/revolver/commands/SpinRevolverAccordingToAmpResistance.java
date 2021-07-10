package frc.robot.revolver.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.revolver.Revolver;

import static frc.robot.revolver.RevolverConstants.UNCLOG_SPEED;

public class SpinRevolverAccordingToAmpResistance extends SequentialCommandGroup {

    public SpinRevolverAccordingToAmpResistance(Revolver revolver, Command command) {
        super(new DoUntilStuck(revolver, command), new UnclogRevolver(revolver, () -> UNCLOG_SPEED));
    }

    @Override
    public boolean isFinished() {
        if (super.isFinished()) {
            initialize();
        }
        return false;
    }
}

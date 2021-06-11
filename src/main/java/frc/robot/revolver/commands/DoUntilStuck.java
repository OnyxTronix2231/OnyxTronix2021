package frc.robot.revolver.commands;

import static frc.robot.revolver.RevolverConstants.UNCLOG_CHECK_DELAY;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.revolver.Revolver;

public class DoUntilStuck extends ParallelDeadlineGroup {

    public DoUntilStuck(Revolver revolver, Command command) {
        super(new WaitCommand(UNCLOG_CHECK_DELAY).andThen(new WaitUntilCommand(revolver::isStuck)), command);
    }
}

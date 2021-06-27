package frc.robot.revolver.commands;

import static frc.robot.revolver.RevolverConstants.UNCLOG_TIME;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.revolver.Revolver;

import java.util.function.DoubleSupplier;

public class UnclogRevolver extends ParallelDeadlineGroup {

    public UnclogRevolver(Revolver revolver, DoubleSupplier speedSupplier) {
        super(new WaitCommand(UNCLOG_TIME), new SpinRevolverBySpeed(revolver, speedSupplier));
    }
}

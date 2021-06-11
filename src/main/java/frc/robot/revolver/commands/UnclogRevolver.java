package frc.robot.revolver.commands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.revolver.Revolver;

import java.util.function.DoubleSupplier;

public class UnclogRevolver extends ParallelRaceGroup {

    public UnclogRevolver(Revolver revolver, DoubleSupplier speedSupplier) {
        super(new WaitCommand(0.2), new SpinRevolverBySpeed(revolver, speedSupplier));
    }
}

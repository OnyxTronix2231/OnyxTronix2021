package frc.robot.crossPlatform;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.collector.Collector;
import frc.robot.collector.commands.OpenAndCollect;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.SpinRevolverAccordingToAmpResistance;
import frc.robot.revolver.commands.SpinRevolverByRPM;

import java.util.function.DoubleSupplier;

public class CollectAndSpinRevolver extends ParallelCommandGroup {

    public CollectAndSpinRevolver(Collector collector, Revolver revolver, DoubleSupplier revolverRPMSupplier,
                                  DoubleSupplier collectorSpeedSupplier) {
        super(new OpenAndCollect(collector, collectorSpeedSupplier),
            new SpinRevolverAccordingToAmpResistance(revolver,
                new SpinRevolverByRPM(revolver, revolverRPMSupplier)));
    }
}

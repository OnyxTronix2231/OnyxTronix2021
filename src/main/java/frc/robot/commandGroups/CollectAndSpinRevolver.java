package frc.robot.commandGroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.collector.Collector;
import frc.robot.collector.commands.OpenAndCollect;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.MoveRevolverByRPM;

import java.util.function.DoubleSupplier;

public class CollectAndSpinRevolver extends ParallelCommandGroup {

    public CollectAndSpinRevolver(Collector collector, Revolver revolver, DoubleSupplier RPMSupplier,
                                  DoubleSupplier SpeedSupplier) {
        super(new OpenAndCollect(collector, SpeedSupplier), new MoveRevolverByRPM(revolver, RPMSupplier));
    }
}

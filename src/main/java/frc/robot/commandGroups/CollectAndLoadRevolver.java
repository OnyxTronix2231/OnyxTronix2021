package frc.robot.commandGroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.collector.Collector;
import frc.robot.collector.commands.OpenAndCollect;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.MoveRevolverBySpeed;

import java.util.function.DoubleSupplier;

public class CollectAndLoadRevolver extends ParallelCommandGroup {

    public CollectAndLoadRevolver(Collector collector, Revolver revolver, DoubleSupplier revolverSpeedSupplier,
                                  DoubleSupplier collectorSpeedSupplier) {
        super(new OpenAndCollect(collector, collectorSpeedSupplier), new MoveRevolverBySpeed(revolver,
                revolverSpeedSupplier));
    }
}

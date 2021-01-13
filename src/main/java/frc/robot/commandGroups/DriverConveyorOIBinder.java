package frc.robot.commandGroups;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.collector.Collector;
import frc.robot.revolver.Revolver;
import frc.robot.ballTrigger.BallTrigger;

public class DriverConveyorOIBinder {
    public DriverConveyorOIBinder(Collector collector, BallTrigger ballTrigger, Revolver revolver,
                                  Trigger collectAndLoadRevolver, Trigger spinRevolverAndTriggerWheels) {
        collectAndLoadRevolver.whileActiveOnce(new CollectAndLoadRevolver(collector, revolver, () -> 0.2, () -> 0.8));

        spinRevolverAndTriggerWheels.whileActiveOnce(new SpinRevolverAndTriggerWheels(ballTrigger, revolver, () -> 0.8,
                () -> 0.8));
    }
}

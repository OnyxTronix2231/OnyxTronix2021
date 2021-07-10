package frc.robot.collector;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.collector.commands.CloseCollectorPistons;
import frc.robot.collector.commands.CollectBySpeed;

import static frc.robot.collector.CollectorConstants.BallCollectorConstantsA.COLLECTION_EJECT_SPEED;

public class DeputeCollectorOiBinder {

    public DeputeCollectorOiBinder(Collector collector, Trigger closeCollector, Trigger ejectBall){
        closeCollector.whenActive(new CloseCollectorPistons(collector));
        ejectBall.whileActiveContinuous(new CollectBySpeed(collector, ()-> COLLECTION_EJECT_SPEED))
    }
}

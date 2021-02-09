package frc.robot.crossPlatform.conveyor;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.CloseBallTriggerPiston;
import frc.robot.collector.Collector;
import frc.robot.collector.commands.CloseCollectorPistons;
import frc.robot.revolver.Revolver;

import static frc.robot.crossPlatform.conveyor.ConveyorConstants.ConveyorConstantsA.*;

public class DriverConveyorOIBinder {

    public DriverConveyorOIBinder(Collector collector, BallTrigger ballTrigger, Revolver revolver,
                                  Trigger collectAndLoadRevolver, Trigger spinRevolverAndTrigger,
                                  Trigger spinRevolverAndTriggerThenOpenPiston) {
        collectAndLoadRevolver.whileActiveOnce(new CollectAndSpinRevolver(collector, revolver,
                () -> REVOLVER_RPM_WHILE_COLLECTING, () -> TESTING_SPEED));
        collectAndLoadRevolver.whenInactive(new CloseCollectorPistons(collector));

        spinRevolverAndTrigger.whileActiveOnce(new SpinRevolverAndTrigger(ballTrigger, revolver,
                () -> TESTING_SPEED, () -> TESTING_SPEED));

        spinRevolverAndTriggerThenOpenPiston.whileActiveContinuous(new SpinRevolverAndTriggerThenOpenTriggerPiston(
                revolver, ballTrigger, () -> REVOLVER_RPM_WHILE_SHOOTING, () -> TESTING_SPEED));
        spinRevolverAndTriggerThenOpenPiston.whenInactive(new CloseBallTriggerPiston(ballTrigger));
    }
}

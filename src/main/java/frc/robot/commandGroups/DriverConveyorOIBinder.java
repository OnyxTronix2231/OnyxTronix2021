package frc.robot.commandGroups;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.CloseBallTriggerPistons;
import frc.robot.collector.Collector;
import frc.robot.collector.commands.CloseCollectorPistons;
import frc.robot.revolver.Revolver;

import static frc.robot.revolver.RevolverConstants.RevolverComponentsA.RPM_WHILE_COLLECTING;
import static frc.robot.revolver.RevolverConstants.RevolverComponentsA.RPM_WHILE_SHOOTING;

public class DriverConveyorOIBinder {
    public DriverConveyorOIBinder(Collector collector, BallTrigger ballTrigger, Revolver revolver,
                                  Trigger collectAndLoadRevolver, Trigger spinRevolverAndTriggerWheels,
                                  Trigger spinRevolverAndTriggerThenOpenPiston) {
        collectAndLoadRevolver.whileActiveOnce(new CollectAndSpinRevolver(collector, revolver,
                () -> RPM_WHILE_COLLECTING, () -> 0.8));
        collectAndLoadRevolver.whenInactive(new CloseCollectorPistons(collector));

        spinRevolverAndTriggerWheels.whileActiveOnce(new SpinRevolverAndTriggerWheels(ballTrigger, revolver, () -> 0.8,
                () -> 0.8));

        spinRevolverAndTriggerThenOpenPiston.whileActiveContinuous(new SpinRevolverAndTriggerThenOpenTriggerPiston(
                revolver, ballTrigger, () -> RPM_WHILE_SHOOTING, () -> 0.8));
        spinRevolverAndTriggerThenOpenPiston.whenInactive(new CloseBallTriggerPistons(ballTrigger));
    }
}

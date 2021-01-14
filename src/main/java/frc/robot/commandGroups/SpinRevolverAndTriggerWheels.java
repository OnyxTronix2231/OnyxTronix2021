package frc.robot.commandGroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.MoveTriggerBySpeed;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.MoveRevolverBySpeed;

import java.util.function.DoubleSupplier;

public class SpinRevolverAndTriggerWheels extends ParallelCommandGroup {
    public SpinRevolverAndTriggerWheels(BallTrigger ballTrigger, Revolver revolver, DoubleSupplier triggerSpeedSupplier,
                                        DoubleSupplier revolverSpeedSupplier) {
        super(new MoveRevolverBySpeed(revolver, revolverSpeedSupplier), new MoveTriggerBySpeed(ballTrigger,
                triggerSpeedSupplier));
    }
}

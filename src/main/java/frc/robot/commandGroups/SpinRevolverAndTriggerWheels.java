package frc.robot.commandGroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.SpinBySpeed;
import frc.robot.revolver.Revolver;

import java.util.function.DoubleSupplier;

public class SpinRevolverAndTriggerWheels extends ParallelCommandGroup {

    public SpinRevolverAndTriggerWheels(BallTrigger ballTrigger, Revolver revolver, DoubleSupplier triggerSpeedSupplier,
                                        DoubleSupplier revolverSpeedSupplier) {
        super(new frc.robot.revolver.commands.SpinBySpeed(revolver, revolverSpeedSupplier), new SpinBySpeed(ballTrigger,
                triggerSpeedSupplier));
    }
}

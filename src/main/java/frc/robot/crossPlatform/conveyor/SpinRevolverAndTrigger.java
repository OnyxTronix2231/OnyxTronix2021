package frc.robot.crossPlatform.conveyor;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.SpinBallTriggerBySpeed;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.SpinRevolverBySpeed;

import java.util.function.DoubleSupplier;

public class SpinRevolverAndTrigger extends ParallelCommandGroup {

    public SpinRevolverAndTrigger(BallTrigger ballTrigger, Revolver revolver, DoubleSupplier triggerSpeedSupplier,
                                  DoubleSupplier revolverSpeedSupplier) {
        super(new SpinRevolverBySpeed(revolver, revolverSpeedSupplier), new SpinBallTriggerBySpeed(ballTrigger,
                triggerSpeedSupplier));
    }
}

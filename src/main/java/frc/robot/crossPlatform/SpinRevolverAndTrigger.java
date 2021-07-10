package frc.robot.crossPlatform;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.SpinBallTriggerBySpeed;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.SpinRevolverAccordingToAmpResistance;
import frc.robot.revolver.commands.SpinRevolverBySpeed;

import java.util.function.DoubleSupplier;

public class SpinRevolverAndTrigger extends ParallelCommandGroup {

    public SpinRevolverAndTrigger(Revolver revolver, BallTrigger ballTrigger, DoubleSupplier triggerSpeedSupplier,
                                  DoubleSupplier revolverSpeedSupplier) {
        super(new SpinRevolverAccordingToAmpResistance(revolver,
                new SpinRevolverBySpeed(revolver, revolverSpeedSupplier)), new SpinBallTriggerBySpeed(ballTrigger,
                triggerSpeedSupplier));
    }
}

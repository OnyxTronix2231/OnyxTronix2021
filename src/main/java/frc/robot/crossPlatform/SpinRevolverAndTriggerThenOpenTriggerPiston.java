package frc.robot.crossPlatform;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.OpenBallTriggerPiston;
import frc.robot.ballTrigger.commands.SpinBallTriggerBySpeed;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.SpinRevolverAccordingToAmpResistance;
import frc.robot.revolver.commands.SpinRevolverByRPM;
import frc.robot.revolver.commands.SpinRevolverBySpeed;

import java.util.function.DoubleSupplier;

public class SpinRevolverAndTriggerThenOpenTriggerPiston extends ParallelCommandGroup {

    public SpinRevolverAndTriggerThenOpenTriggerPiston(Revolver revolver, BallTrigger ballTrigger,
                                                       DoubleSupplier revolverSpeedSupplier,
                                                       DoubleSupplier ballTriggerSpeedSupplier) {
        super(new SpinRevolverAccordingToAmpResistance(revolver,
                new SpinRevolverBySpeed(revolver, revolverSpeedSupplier)),
            new SpinBallTriggerBySpeed(ballTrigger, ballTriggerSpeedSupplier),
            new WaitUntilCommand(revolver::isOnTarget).andThen(new OpenBallTriggerPiston(ballTrigger)));
    }
}

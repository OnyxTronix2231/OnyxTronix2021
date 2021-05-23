package frc.robot.crossPlatform;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.OpenBallTriggerPiston;
import frc.robot.ballTrigger.commands.SpinBallTriggerBySpeed;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.SpinRevolverByRPM;

import java.util.function.DoubleSupplier;

public class SpinRevolverAndTriggerThenOpenTriggerPiston extends ParallelCommandGroup {

    public SpinRevolverAndTriggerThenOpenTriggerPiston(Revolver revolver, BallTrigger ballTrigger,
                                                       DoubleSupplier rpmSupplier,
                                                       DoubleSupplier ballTriggerSpeedSupplier) {
        super(new SpinRevolverByRPM(revolver, rpmSupplier), new SpinBallTriggerBySpeed(ballTrigger, ballTriggerSpeedSupplier),
                new WaitUntilCommand(revolver::isOnTarget).andThen(new OpenBallTriggerPiston(ballTrigger)));
    }
}

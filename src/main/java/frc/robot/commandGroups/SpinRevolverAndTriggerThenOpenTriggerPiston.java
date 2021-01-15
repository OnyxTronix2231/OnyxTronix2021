package frc.robot.commandGroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.MoveBallTriggerBySpeed;
import frc.robot.ballTrigger.commands.OpenBallTriggerPistons;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.MoveRevolverByRPM;

import java.util.function.DoubleSupplier;

public class SpinRevolverAndTriggerThenOpenTriggerPiston extends ParallelCommandGroup {
    public SpinRevolverAndTriggerThenOpenTriggerPiston(Revolver revolver, BallTrigger ballTrigger,
                                                       DoubleSupplier RPMSupplier, DoubleSupplier speedSupplier) {
        super(new MoveRevolverByRPM(revolver, RPMSupplier), new MoveBallTriggerBySpeed(ballTrigger, speedSupplier),
                new WaitUntilCommand(revolver::isOnTarget).andThen(new OpenBallTriggerPistons(ballTrigger)));
    }
}

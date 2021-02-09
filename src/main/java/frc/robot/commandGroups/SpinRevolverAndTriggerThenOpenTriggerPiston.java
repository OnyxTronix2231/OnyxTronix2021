package frc.robot.commandGroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.SpinBySpeed;
import frc.robot.ballTrigger.commands.OpenPiston;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.SpinByRPM;

import java.util.function.DoubleSupplier;

public class SpinRevolverAndTriggerThenOpenTriggerPiston extends ParallelCommandGroup {

    public SpinRevolverAndTriggerThenOpenTriggerPiston(Revolver revolver, BallTrigger ballTrigger,
                                                       DoubleSupplier RPMSupplier, DoubleSupplier speedSupplier) {
        super(new SpinByRPM(revolver, RPMSupplier), new SpinBySpeed(ballTrigger, speedSupplier),
                new WaitUntilCommand(revolver::isOnTarget).andThen(new OpenPiston(ballTrigger)));
    }
}

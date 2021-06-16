package frc.robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.ballTrigger.BallTrigger;

import java.util.Arrays;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class ControlBallTriggerByConditions extends SequentialCommandGroup {

    private BallTrigger ballTrigger;

    public ControlBallTriggerByConditions(BallTrigger ballTrigger, DoubleSupplier ballTriggerSpeedSupplier,
                                          BooleanSupplier... isReadyConditions) {
        super(
                new WaitUntilCommand(() -> Arrays.stream(isReadyConditions).allMatch(BooleanSupplier::getAsBoolean)),
                new OpenBallTriggerPiston(ballTrigger),
//                new SpinBallTriggerBySpeed(ballTrigger, ballTriggerSpeedSupplier),
                new WaitUntilCommand(() -> Arrays.stream(isReadyConditions).
                        anyMatch(isReadyCondition -> !isReadyCondition.getAsBoolean())),
                new CloseBallTriggerPiston(ballTrigger));
        this.ballTrigger = ballTrigger;
    }

    @Override
    public boolean isFinished() {
        if (super.isFinished()) {
            initialize();
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        CommandScheduler.getInstance().schedule(new CloseBallTriggerPiston(ballTrigger));
    }
}

package frc.robot.ballTrigger.commands;

import static frc.robot.crossPlatform.CrossPlatformConstants.TriggerConstantsA.BALL_TRIGGER_RPM;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.ballTrigger.BallTrigger;

import java.util.Arrays;
import java.util.function.BooleanSupplier;

public class ControlBallTriggerByConditions extends SequentialCommandGroup {

    private BallTrigger ballTrigger;

    public ControlBallTriggerByConditions(BallTrigger ballTrigger, BooleanSupplier... isReadyConditions) {
        super(
                new WaitUntilCommand(() -> Arrays.stream(isReadyConditions).allMatch(BooleanSupplier::getAsBoolean)),
                new OpenBallTriggerPiston(ballTrigger));
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

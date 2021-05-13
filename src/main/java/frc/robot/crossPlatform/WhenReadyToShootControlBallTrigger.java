package frc.robot.crossPlatform;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.arc.Arc;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.CloseBallTriggerPiston;
import frc.robot.ballTrigger.commands.OpenBallTriggerPiston;
import frc.robot.ballTrigger.commands.SpinBallTriggerBySpeed;
import frc.robot.shooter.Shooter;
import frc.robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class WhenReadyToShootControlBallTrigger extends SequentialCommandGroup {

    public WhenReadyToShootControlBallTrigger(BallTrigger ballTrigger, Shooter shooter, Arc arc, Turret turret,
                                              DoubleSupplier ballTriggerSpeedSupplier) {
        super(
                new WaitUntilCommand(() -> shooter.isOnTarget() && arc.isOnTarget() && turret.isOnTarget()),
                new OpenBallTriggerPiston(ballTrigger),
                new SpinBallTriggerBySpeed(ballTrigger, ballTriggerSpeedSupplier),
                new WaitUntilCommand(() -> !shooter.isOnTarget() && !arc.isOnTarget() && !turret.isOnTarget()),
                new CloseBallTriggerPiston(ballTrigger));
    }

    @Override
    public void end(boolean interrupted) {
        if (this.isFinished()) {
            CommandScheduler.getInstance().schedule(this);
        }
    }
}

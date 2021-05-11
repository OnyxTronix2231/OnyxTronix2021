package frc.robot.crossPlatform;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.arc.Arc;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.CloseBallTriggerPiston;
import frc.robot.ballTrigger.commands.OpenBallTriggerPiston;
import frc.robot.shooter.Shooter;

public class CloseOrOpenPistonAccordingToData extends SequentialCommandGroup {

  public CloseOrOpenPistonAccordingToData(BallTrigger ballTrigger, Shooter shooter, Arc arc) {
    super(
        new WaitUntilCommand(() -> shooter.isOnTarget() && arc.isOnTarget()).alongWith(),
        //TODO: need wait until turret isOnTarget
        new OpenBallTriggerPiston(ballTrigger),
        new WaitUntilCommand(() -> !shooter.isOnTarget() && !arc.isOnTarget()), //TODO: same
        new CloseBallTriggerPiston(ballTrigger));
  }

  @Override
  public void end(boolean interrupted) {
    if (this.isFinished()) {
      CommandScheduler.getInstance().schedule(this);
    }
  }
}

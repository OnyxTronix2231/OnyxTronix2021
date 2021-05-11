package frc.robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ballTrigger.BallTrigger;

public class CloseBallTriggerPistonAndStopBallTrigger extends CommandBase {

  private final BallTrigger ballTrigger;

  public CloseBallTriggerPistonAndStopBallTrigger(BallTrigger ballTrigger) {
    this.ballTrigger = ballTrigger;
  }

  @Override
  public void initialize() {
    ballTrigger.closePiston();
    ballTrigger.stop();
  }
}

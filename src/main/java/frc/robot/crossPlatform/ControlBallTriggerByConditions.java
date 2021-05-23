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

import java.util.Arrays;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class ControlBallTriggerByConditions extends SequentialCommandGroup {

  private BallTrigger ballTrigger;

  public ControlBallTriggerByConditions(BallTrigger ballTrigger, Shooter shooter, Arc arc, Turret turret,
                                        DoubleSupplier ballTriggerSpeedSupplier, BooleanSupplier... isReadyConditions) {
    super(
        new WaitUntilCommand(() -> Arrays.stream(isReadyConditions).allMatch(BooleanSupplier::getAsBoolean)),
        new OpenBallTriggerPiston(ballTrigger),
        new SpinBallTriggerBySpeed(ballTrigger, ballTriggerSpeedSupplier),
        new WaitUntilCommand(() -> Arrays.stream(isReadyConditions).anyMatch(isReadyCondition -> !isReadyCondition.getAsBoolean())),
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

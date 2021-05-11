package frc.robot.crossPlatform;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.arc.Arc;
import frc.robot.arc.commands.MoveArcToDistance;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.CloseBallTriggerPiston;
import frc.robot.revolver.Revolver;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.SpinShooterByDistance;

import java.util.function.DoubleSupplier;

public class ShootBall extends ParallelCommandGroup {

  private final BallTrigger ballTrigger;

  public ShootBall(Shooter shooter, BallTrigger ballTrigger, Arc arc, Revolver revolver,
                   DoubleSupplier distanceSupplier, DoubleSupplier ballTriggerSpeedSupplier,
                   DoubleSupplier revolverSpeedSupplier) {
    super(
        new SpinRevolverAndTrigger(ballTrigger, revolver, revolverSpeedSupplier, ballTriggerSpeedSupplier),
        new SpinShooterByDistance(shooter, distanceSupplier),
        new MoveArcToDistance(arc, distanceSupplier),
        new CloseOrOpenPistonAccordingToData(ballTrigger, shooter, arc));
    this.ballTrigger = ballTrigger;
  }

  @Override
  public void end(boolean interrupted) {
    new CloseBallTriggerPiston(ballTrigger);
  }
}

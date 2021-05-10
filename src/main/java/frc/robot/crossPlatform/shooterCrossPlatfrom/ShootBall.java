package frc.robot.crossPlatform.shooterCrossPlatfrom;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.arc.Arc;
import frc.robot.arc.commands.MoveArcToDistance;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.crossPlatform.conveyor.SpinRevolverAndTriggerThenOpenTriggerPiston;
import frc.robot.revolver.Revolver;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.SpinShooterByDistance;

import java.util.function.DoubleSupplier;

public class ShootBall extends ParallelCommandGroup {

    public ShootBall(Shooter shooter, BallTrigger ballTrigger, Arc arc, Revolver revolver,
                     DoubleSupplier distanceSupplier, DoubleSupplier ballTriggerSpeedSupplier,
                     DoubleSupplier revolverSpeedSuplier) {
        super(new SpinShooterByDistance(shooter, distanceSupplier),
                new MoveArcToDistance(arc, distanceSupplier),
                new SequentialCommandGroup(new WaitUntilCommand(shooter::isOnTarget),
                        new WaitUntilCommand(arc::isOnTarget)),
                new SpinRevolverAndTriggerThenOpenTriggerPiston(revolver, ballTrigger,
                        revolverSpeedSuplier, ballTriggerSpeedSupplier));
    }
}

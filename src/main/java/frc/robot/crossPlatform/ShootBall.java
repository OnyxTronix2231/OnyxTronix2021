package frc.robot.crossPlatform;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.arc.commands.MoveArcAndCloseByTrigger;
import frc.robot.arc.commands.MoveArcByVision;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.ControlBallTriggerByConditions;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.SpinShooterByRPM;
import frc.robot.shooter.commands.SpinShooterByVision;
import frc.robot.turret.Turret;
import frc.robot.turret.commands.MoveTurretByVision;
import frc.robot.vision.visionMainChallenge.Vision;

import java.util.function.DoubleSupplier;

public class ShootBall extends ParallelCommandGroup {

    private final BallTrigger ballTrigger;

    public ShootBall(Shooter shooter, BallTrigger ballTrigger, Arc arc,
                     Turret turret, Vision vision, DoubleSupplier ballTriggerSpeedSupplier, Trigger shootBall) {
        super(
                //new SpinShooterByVision(shooter, vision),
                //new MoveArcByVision(arc, shootBall, vision),
                new MoveTurretByVision(turret, vision));
                NetworkTableEntry entry = Shuffleboard.getTab("Arc")
                        .add("angle", 0).getEntry();
                NetworkTableEntry shooterRpm = Shuffleboard.getTab("Arc")
                .add("angle", 0).getEntry();
                new SpinShooterByRPM(shooter, ()-> shooterRpm.getDouble(0));
                new MoveArcAndCloseByTrigger(arc, shootBall, ()-> entry.getDouble(20));
                new ControlBallTriggerByConditions(ballTrigger, ballTriggerSpeedSupplier,
                        shooter::isOnTarget, arc::isOnTarget, turret::isOnTarget);
        this.ballTrigger = ballTrigger;
    }
}

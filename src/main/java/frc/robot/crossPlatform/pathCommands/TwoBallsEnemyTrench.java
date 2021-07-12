package frc.robot.crossPlatform.pathCommands;

import static frc.robot.crossPlatform.CrossPlatformConstants.CollectorConstantsA.TESTING_SPEED_COLLECTOR;
import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_RPM_WHILE_COLLECTING;
import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.PRIORITY_PATH_A;
import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.PRIORITY_PATH_B;
import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.PRIORITY_PATH_C;
import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.PRIORITY_PATH_D;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.arc.Arc;
import frc.robot.arc.commands.CalibrateArc;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.collector.Collector;
import frc.robot.crossPlatform.AutonomousShootBalls;
import frc.robot.crossPlatform.CollectAndSpinRevolver;
import frc.robot.crossPlatform.ShootBallClose;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.commands.MoveByPath;
import frc.robot.drivetrain.commands.ResetOdometryToPose;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.SpinRevolverUntilLimitSwitch;
import frc.robot.shooter.Shooter;
import frc.robot.vision.visionMainChallenge.Vision;
import frc.robot.yawControll.YawControl;

public class TwoBallsEnemyTrench extends SequentialCommandGroup {

    public TwoBallsEnemyTrench(DriveTrain driveTrain, Collector collector, Revolver revolver,
                               BallTrigger ballTrigger, Shooter shooter, Arc arc, Vision vision,
                               YawControl yawControl) {
        super(new ResetOdometryToPose(driveTrain, PRIORITY_PATH_A.getStartPose()),
                new CalibrateArc(arc),
                new SpinRevolverUntilLimitSwitch(revolver),
                new MoveByPath(driveTrain, PRIORITY_PATH_A).
                        raceWith(new CollectAndSpinRevolver(collector, revolver, () -> REVOLVER_RPM_WHILE_COLLECTING,
                                () -> TESTING_SPEED_COLLECTOR)),
                new MoveByPath(driveTrain, PRIORITY_PATH_B).
                        raceWith(new CollectAndSpinRevolver(collector, revolver, () -> REVOLVER_RPM_WHILE_COLLECTING,
                                () -> TESTING_SPEED_COLLECTOR)),
                new MoveByPath(driveTrain, PRIORITY_PATH_C).
                        raceWith(new CollectAndSpinRevolver(collector, revolver, () -> REVOLVER_RPM_WHILE_COLLECTING,
                                () -> TESTING_SPEED_COLLECTOR)),
                new MoveByPath(driveTrain, PRIORITY_PATH_D).
                        raceWith(new CollectAndSpinRevolver(collector, revolver, () -> REVOLVER_RPM_WHILE_COLLECTING,
                                () -> TESTING_SPEED_COLLECTOR)),
                new AutonomousShootBalls(ballTrigger, vision, arc, yawControl, shooter, revolver)
                );
    }
}

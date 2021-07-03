package frc.robot.crossPlatform.pathCommands;

import static frc.robot.crossPlatform.CrossPlatformConstants.CollectorConstantsA.TESTING_SPEED_COLLECTOR;
import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_RPM_WHILE_COLLECTING;
import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.THREE_BALLS_OUR_TRENCH_A;
import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.THREE_BALLS_OUR_TRENCH_B;
import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargeStartPoints.SECOND_PRIORITY_PATH_START;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.collector.Collector;
import frc.robot.crossPlatform.AutonomousShootBalls;
import frc.robot.crossPlatform.CollectAndSpinRevolver;
import frc.robot.crossPlatform.ShootBall;
import frc.robot.crossPlatform.ShootBallClose;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.commands.MoveByPath;
import frc.robot.drivetrain.commands.ResetOdometryToPose;
import frc.robot.revolver.Revolver;
import frc.robot.shooter.Shooter;
import frc.robot.vision.visionMainChallenge.Vision;
import frc.robot.yawControll.YawControl;
import frc.robot.yawControll.commands.MoveTurretToTargetArea;

public class ThreeBallsOurTrench extends SequentialCommandGroup {

    public ThreeBallsOurTrench(DriveTrain driveTrain, Collector collector, Revolver revolver,
                               BallTrigger ballTrigger, Shooter shooter, Arc arc, Vision vision,
                               YawControl yawControl) {
        super(
//                new ResetOdometryToPose(driveTrain, SECOND_PRIORITY_PATH_START),
//                new MoveByPath(driveTrain, THREE_BALLS_OUR_TRENCH_A).raceWith(new CollectAndSpinRevolver(collector,
//                        revolver, () -> REVOLVER_RPM_WHILE_COLLECTING,
//                        () -> TESTING_SPEED_COLLECTOR)),
//                new CollectAndSpinRevolver(collector, revolver, () -> REVOLVER_RPM_WHILE_COLLECTING,
//                        () -> TESTING_SPEED_COLLECTOR).raceWith(new WaitCommand(3)),
//                new AutonomousShootBalls(ballTrigger, vision, arc, yawControl, shooter, revolver),
//                new MoveByPath(driveTrain, THREE_BALLS_OUR_TRENCH_B).raceWith(new CollectAndSpinRevolver(collector,
//                        revolver, () -> REVOLVER_RPM_WHILE_COLLECTING,
//                        () -> TESTING_SPEED_COLLECTOR)),
//                new CollectAndSpinRevolver(collector, revolver, () -> REVOLVER_RPM_WHILE_COLLECTING,
//                        () -> TESTING_SPEED_COLLECTOR).raceWith(new WaitCommand(3)),
                new AutonomousShootBalls(ballTrigger, vision, arc, yawControl, shooter, revolver)
                );
    }
}

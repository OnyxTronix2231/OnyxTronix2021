package frc.robot.crossPlatform.pathCommands;

import static frc.robot.crossPlatform.CrossPlatformConstants.CollectorConstantsA.TESTING_SPEED_COLLECTOR;
import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_RPM_WHILE_COLLECTING;
import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.PRIORITY_PATH_A;
import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.PRIORITY_PATH_B;
import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.PRIORITY_PATH_C;
import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.PRIORITY_PATH_D;
import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.PRIORITY_PATH_E;

import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.collector.Collector;
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

public class PriorityPathCommand extends SequentialCommandGroup {

    public PriorityPathCommand(DriveTrain driveTrain, Collector collector, Revolver revolver,
                               BallTrigger ballTrigger, Shooter shooter, Arc arc, Vision vision, YawControl yawControl,
                               Trigger shootBall) {
        super(new ResetOdometryToPose(driveTrain, PRIORITY_PATH_A.getStartPose()),
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
                new ShootBallClose(shooter, yawControl, ballTrigger, revolver).raceWith(
                        new WaitCommand(5)
                ),
//                new MoveTurretToTargetArea(yawControl).
//                        raceWith(new CollectAndSpinRevolver(collector, revolver, () -> REVOLVER_RPM_WHILE_COLLECTING,
//                        () -> TESTING_SPEED_COLLECTOR), new WaitCommand(1)),
                new PrintCommand(" " + driveTrain.getPose().getX() + " " + driveTrain.getPose().getX()
                        + " " + driveTrain.getPose().getRotation().getDegrees()),
//                new UpdateRobotPose(driveTrain, vision),
//                new PrintCommand(" " + driveTrain.getPose().getX() + " " + driveTrain.getPose().getX()
//                        + " " + driveTrain.getPose().getRotation().getDegrees()),
                new MoveByPath(driveTrain, PRIORITY_PATH_E).
                        raceWith(new CollectAndSpinRevolver(collector, revolver, () -> REVOLVER_RPM_WHILE_COLLECTING,
                                () -> TESTING_SPEED_COLLECTOR)),
                new ShootBallClose(shooter, yawControl, ballTrigger, revolver).raceWith(
                        new WaitCommand(5)
                ),
                new PrintCommand(" " + driveTrain.getPose().getX() + " " + driveTrain.getPose().getX()
                        + " " + driveTrain.getPose().getRotation().getDegrees())
//                new UpdateRobotPose(driveTrain, vision),
//                new PrintCommand(" " + driveTrain.getPose().getX() + " " + driveTrain.getPose().getX()
//                        + " " + driveTrain.getPose().getRotation().getDegrees())
                );
    }
}

package frc.robot.crossPlatform.pathCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.arc.Arc;
import frc.robot.arc.commands.CalibrateArc;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.collector.Collector;
import frc.robot.crossPlatform.AutonomousShootBalls;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.commands.MoveByPath;
import frc.robot.drivetrain.commands.ResetOdometryToPose;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.SpinRevolverUntilLimitSwitch;
import frc.robot.shooter.Shooter;
import frc.robot.vision.visionMainChallenge.Vision;
import frc.robot.yawControll.YawControl;

import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.ONE_METER_FORWARD;
import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargeStartPoints.NEUTRAL_START;
import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargeStartPoints.SECOND_PRIORITY_PATH_START;

public class MoveFromLineAndShoot extends SequentialCommandGroup {

    public MoveFromLineAndShoot(DriveTrain driveTrain, Collector collector, Revolver revolver,
                                BallTrigger ballTrigger, Shooter shooter, Arc arc, Vision vision,
                                YawControl yawControl){
        super(
        new CalibrateArc(arc),
        new SpinRevolverUntilLimitSwitch(revolver),
        new ResetOdometryToPose(driveTrain, NEUTRAL_START),
        new MoveByPath(driveTrain, ONE_METER_FORWARD),
        new AutonomousShootBalls(ballTrigger, vision, arc, yawControl, shooter, revolver));
    }
}

package frc.robot.crossPlatform;

import static frc.robot.crossPlatform.CrossPlatformConstants.CollectorConstantsA.TESTING_SPEED_COLLECTOR;
import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_RPM_WHILE_COLLECTING;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.arc.commands.CalibrateArc;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.SpinBallTriggerBySpeed;
import frc.robot.collector.Collector;
import frc.robot.collector.commands.CloseCollectorPistons;
import frc.robot.collector.commands.OpenCollectorPistons;
import frc.robot.crossPlatform.pathCommands.DriveOneMeter;
import frc.robot.crossPlatform.pathCommands.DrivePriority;
import frc.robot.crossPlatform.pathCommands.PriorityPathCommand;
import frc.robot.crossPlatform.pathCommands.ThreeBallsOurTrench;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.revolver.Revolver;
import frc.robot.shooter.Shooter;
import frc.robot.vision.visionMainChallenge.Vision;
import frc.robot.yawControll.YawControl;
import onyxTronix.JoystickAxis;

public class DriverCrossPlatformOIBinder {

    public DriverCrossPlatformOIBinder(DriveTrain driveTrain, Collector collector, BallTrigger ballTrigger, Revolver revolver, Arc arc,
                                       YawControl yawControl, Shooter shooter, Vision vision, Trigger collectAndLoadRevolver,
                                       Trigger shootBallTrigger, JoystickAxis moveBallTrigger, Trigger calibrateArc,
                                       Trigger shootClose, Trigger doPath) {
        collectAndLoadRevolver.whileActiveOnce(new CollectAndSpinRevolver(collector, revolver,
                () -> REVOLVER_RPM_WHILE_COLLECTING, () -> TESTING_SPEED_COLLECTOR
        ));

//        shootBallTrigger.whileActiveContinuous(new ShootBall(shooter, ballTrigger, arc, yawControl, vision, revolver,
//                shootBallTrigger));

        moveBallTrigger.whileActiveContinuous(new SpinBallTriggerBySpeed(ballTrigger, moveBallTrigger::getRawAxis));

        calibrateArc.whenActive(new CalibrateArc(arc));

        shootClose.whileActiveContinuous(new ShootBallClose(shooter, yawControl, ballTrigger, revolver));

        doPath.whileActiveContinuous(new ThreeBallsOurTrench(driveTrain, collector, revolver,
                ballTrigger, shooter, arc, vision, yawControl));
    }
}

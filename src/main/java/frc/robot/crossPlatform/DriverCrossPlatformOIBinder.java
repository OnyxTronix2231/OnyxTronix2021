package frc.robot.crossPlatform;

import static frc.robot.crossPlatform.CrossPlatformConstants.CollectorConstantsA.TESTING_SPEED_COLLECTOR;
import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_RPM_WHILE_COLLECTING;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.arc.commands.CalibrateArc;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.SpinBallTriggerByRPM;
import frc.robot.ballTrigger.commands.SpinBallTriggerBySpeed;
import frc.robot.collector.Collector;
import frc.robot.collector.commands.CloseCollectorPistons;
import frc.robot.collector.commands.OpenCollectorPistons;
import frc.robot.revolver.Revolver;
import frc.robot.shooter.Shooter;
import frc.robot.vision.visionMainChallenge.Vision;
import frc.robot.yawControll.YawControl;
import onyxTronix.JoystickAxis;

public class DriverCrossPlatformOIBinder {

    public DriverCrossPlatformOIBinder(Collector collector, BallTrigger ballTrigger, Revolver revolver, Arc arc,
                                       YawControl yawControl, Shooter shooter, Vision vision, Trigger collectAndLoadRevolver,
                                       Trigger shootBallTrigger, Trigger openCollector, JoystickAxis moveBallTrigger,
                                       Trigger changeAngle, Trigger calibrateArc, Trigger shootClose) {
        collectAndLoadRevolver.whileActiveOnce(new CollectAndSpinRevolver(collector, revolver,
                () -> REVOLVER_RPM_WHILE_COLLECTING, () -> TESTING_SPEED_COLLECTOR
        ));

        shootBallTrigger.whileActiveContinuous(new ShootBall(shooter, ballTrigger, arc, yawControl, vision, revolver,
                shootBallTrigger));

        openCollector.whenActive(new OpenCollectorPistons(collector));
        openCollector.whenInactive(new CloseCollectorPistons(collector));

        moveBallTrigger.whileActiveContinuous(new SpinBallTriggerBySpeed(ballTrigger, moveBallTrigger::getRawAxis));

//        changeAngle.whenActive(new MoveArcAndCloseByTrigger(arc, changeAngle, arc::getTestAngle));
        calibrateArc.whenActive(new CalibrateArc(arc));

        shootClose.whileActiveContinuous(new ShootBallClose(shooter, yawControl, ballTrigger, revolver));
    }
}

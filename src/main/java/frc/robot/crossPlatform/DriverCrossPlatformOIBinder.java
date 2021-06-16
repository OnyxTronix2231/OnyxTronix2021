package frc.robot.crossPlatform;

import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_RPM_WHILE_COLLECTING;
import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_RPM_WHILE_SHOOTING;
import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_SPEED_WHILE_COLLECTING;
import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_SPEED_WHILE_SHOOTING;
import static frc.robot.crossPlatform.CrossPlatformConstants.CollectorConstantsA.TESTING_SPEED;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.arc.commands.MoveArcByAngleIfNeeded;
import frc.robot.arc.commands.MoveArcUntilLowerLimitSwitch;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.CloseBallTriggerPiston;
import frc.robot.ballTrigger.commands.OpenBallTriggerPiston;
import frc.robot.ballTrigger.commands.SpinBallTriggerBySpeed;
import frc.robot.collector.Collector;
import frc.robot.collector.commands.CloseCollectorPistons;
import frc.robot.collector.commands.OpenCollectorPistons;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.SpinRevolverAccordingToAmpResistance;
import frc.robot.revolver.commands.SpinRevolverByRPM;
import frc.robot.revolver.commands.SpinRevolverBySpeed;
import frc.robot.shooter.Shooter;
import frc.robot.turret.Turret;
import frc.robot.vision.visionMainChallenge.Vision;
import onyxTronix.JoystickAxis;

public class DriverCrossPlatformOIBinder {

    public DriverCrossPlatformOIBinder(Collector collector, BallTrigger ballTrigger, Revolver revolver, Arc arc,
                                       Turret turret, Shooter shooter, Vision vision, Trigger collectAndLoadRevolver,
                                       Trigger shootBallTrigger, Trigger openCollector, JoystickAxis moveBallTrigger) {
        collectAndLoadRevolver.whileActiveOnce(new CollectAndSpinRevolver(collector, revolver,
                () -> REVOLVER_RPM_WHILE_COLLECTING, () -> TESTING_SPEED));

        shootBallTrigger.whileActiveContinuous(new MoveArcByAngleIfNeeded(arc,
                () -> 30));//.alongWith(new SpinRevolverByRPM(revolver, () -> REVOLVER_RPM_WHILE_SHOOTING)));

        openCollector.whenActive(new MoveArcUntilLowerLimitSwitch(arc));
        //openCollector.whenInactive(new CloseCollectorPistons(collector));

        moveBallTrigger.whileActiveContinuous(new SpinBallTriggerBySpeed(ballTrigger, moveBallTrigger::getRawAxis));
    }
}

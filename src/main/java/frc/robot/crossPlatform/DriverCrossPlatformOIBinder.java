package frc.robot.crossPlatform;

import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_SPEED_WHILE_COLLECTING;
import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_RPM_WHILE_SHOOTING;
import static frc.robot.crossPlatform.CrossPlatformConstants.CollectorConstantsA.TESTING_SPEED;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.CloseBallTriggerPiston;
import frc.robot.ballTrigger.commands.OpenBallTriggerPiston;
import frc.robot.collector.Collector;
import frc.robot.collector.commands.CloseCollectorPistons;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.SpinRevolverBySpeed;
import frc.robot.shooter.Shooter;
import frc.robot.turret.Turret;
import frc.robot.vision.visionMainChallenge.Vision;
import onyxTronix.JoystickAxis;

public class DriverCrossPlatformOIBinder {

    public DriverCrossPlatformOIBinder(Collector collector, BallTrigger ballTrigger, Revolver revolver, Arc arc,
                                       Turret turret, Shooter shooter, Vision vision, Trigger collectAndLoadRevolver,
                                       Trigger spinRevolverAndTrigger, Trigger spinRevolverAndTriggerThenOpenPiston,
                                       Trigger shootBallTrigger, Trigger openBallTrigger, JoystickAxis moveRevolver) {
        collectAndLoadRevolver.whileActiveOnce(new CollectAndSpinRevolver(collector, revolver,
                () -> REVOLVER_SPEED_WHILE_COLLECTING, () -> TESTING_SPEED));
        collectAndLoadRevolver.whenInactive(new CloseCollectorPistons(collector));

        spinRevolverAndTrigger.whileActiveOnce(new SpinRevolverAndTrigger(revolver, ballTrigger,
                () -> TESTING_SPEED, () -> TESTING_SPEED));

        spinRevolverAndTriggerThenOpenPiston.whileActiveContinuous(new SpinRevolverAndTriggerThenOpenTriggerPiston(
                revolver, ballTrigger, () -> REVOLVER_RPM_WHILE_SHOOTING, () -> TESTING_SPEED));
        spinRevolverAndTriggerThenOpenPiston.whenInactive(new CloseBallTriggerPiston(ballTrigger));

        shootBallTrigger.whileActiveContinuous(new ShootBall(shooter, ballTrigger, arc, turret, vision,
                () -> TESTING_SPEED).alongWith(new SpinRevolverBySpeed(revolver, () -> 0.38)));
        shootBallTrigger.whenInactive(new CloseBallTriggerPiston(ballTrigger));

        openBallTrigger.whenActive(new OpenBallTriggerPiston(ballTrigger));
        openBallTrigger.whenInactive(new CloseBallTriggerPiston(ballTrigger));

        moveRevolver.whenActive(new SpinRevolverBySpeed(revolver, () -> (moveRevolver.getRawAxis() *
                moveRevolver.getRawAxis())));
    }
}

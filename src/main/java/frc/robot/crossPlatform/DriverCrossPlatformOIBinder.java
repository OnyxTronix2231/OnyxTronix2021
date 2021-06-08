package frc.robot.crossPlatform;

import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_SPEED_WHILE_COLLECTING;
import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_SPEED_WHILE_SHOOTING;
import static frc.robot.crossPlatform.CrossPlatformConstants.CollectorConstantsA.COLLECTOR_TESTING_SPEED;
import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.TRIGGER_TESTING_SPEED;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.CloseBallTriggerPiston;
import frc.robot.collector.Collector;
import frc.robot.collector.commands.CloseCollectorPistons;
import frc.robot.revolver.Revolver;
import frc.robot.shooter.Shooter;
import frc.robot.turret.Turret;
import frc.robot.vision.visionMainChallenge.Vision;

public class DriverCrossPlatformOIBinder {

    public DriverCrossPlatformOIBinder(Collector collector, BallTrigger ballTrigger, Revolver revolver, Arc arc,
                                       Turret turret, Shooter shooter, Vision vision, Trigger collectAndLoadRevolver,
                                       Trigger spinRevolverAndTrigger, Trigger spinRevolverAndTriggerThenOpenPiston,
                                       Trigger shootBallTrigger) {
        collectAndLoadRevolver.whileActiveOnce(new CollectAndSpinRevolver(collector, revolver,
                () -> REVOLVER_SPEED_WHILE_COLLECTING, () -> COLLECTOR_TESTING_SPEED));
        collectAndLoadRevolver.whenInactive(new CloseCollectorPistons(collector));

        spinRevolverAndTrigger.whileActiveOnce(new SpinRevolverAndTrigger(revolver, ballTrigger,
                () -> COLLECTOR_TESTING_SPEED, () -> COLLECTOR_TESTING_SPEED));

        spinRevolverAndTriggerThenOpenPiston.whileActiveContinuous(new SpinRevolverAndTriggerThenOpenTriggerPiston(
                revolver, ballTrigger, () -> REVOLVER_SPEED_WHILE_SHOOTING, () -> TRIGGER_TESTING_SPEED));
        spinRevolverAndTriggerThenOpenPiston.whenInactive(new CloseBallTriggerPiston(ballTrigger));

        shootBallTrigger.whileActiveContinuous(new ShootBall(shooter, ballTrigger, arc, turret, vision,
                () -> TRIGGER_TESTING_SPEED));
    }
}

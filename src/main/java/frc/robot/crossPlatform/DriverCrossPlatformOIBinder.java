package frc.robot.crossPlatform;

import static frc.robot.arc.ArcConstants.MIN_POSSIBLE_ANGLE;
import static frc.robot.crossPlatform.CrossPlatformConstants.CollectorConstantsA.TESTING_SPEED_COLLECTOR;
import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_RPM_WHILE_COLLECTING;
import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_RPM_WHILE_SHOOTING;
import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_SPEED_WHILE_COLLECTING;
import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_SPEED_WHILE_SHOOTING;
import static frc.robot.crossPlatform.CrossPlatformConstants.TriggerConstantsA.TESTING_SPEED_TRIGGER;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.arc.commands.MoveArcAndCloseByTrigger;
import frc.robot.arc.commands.MoveArcToAngle;
import frc.robot.arc.commands.MoveArcUntilLowerLimitSwitch;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.SpinBallTriggerBySpeed;
import frc.robot.collector.Collector;
import frc.robot.collector.commands.CloseCollectorPistons;
import frc.robot.collector.commands.OpenCollectorPistons;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.SpinRevolverByRPM;
import frc.robot.shooter.Shooter;
import frc.robot.turret.Turret;
import frc.robot.vision.visionMainChallenge.Vision;
import onyxTronix.JoystickAxis;

public class DriverCrossPlatformOIBinder {

    public DriverCrossPlatformOIBinder(Collector collector, BallTrigger ballTrigger, Revolver revolver, Arc arc,
                                       Turret turret, Shooter shooter, Vision vision, Trigger collectAndLoadRevolver,
                                       Trigger shootBallTrigger, Trigger openCollector, JoystickAxis moveBallTrigger,
                                       Trigger changeAngle, Trigger calibrateArc) {
        collectAndLoadRevolver.whileActiveOnce(new CollectAndSpinRevolver(collector, revolver,
                () -> REVOLVER_RPM_WHILE_COLLECTING, () -> TESTING_SPEED_COLLECTOR
        ));

        shootBallTrigger.whileActiveContinuous(new ShootBall(shooter, ballTrigger, arc, turret, vision,
                () -> TESTING_SPEED_TRIGGER, shootBallTrigger).alongWith(new SpinRevolverByRPM(revolver, () -> REVOLVER_RPM_WHILE_SHOOTING)));

        openCollector.whenActive(new OpenCollectorPistons(collector));
        openCollector.whenInactive(new CloseCollectorPistons(collector));

        moveBallTrigger.whileActiveContinuous(new SpinBallTriggerBySpeed(ballTrigger, moveBallTrigger::getRawAxis));

        NetworkTableEntry entry = Shuffleboard.getTab("Arc").add("angle", 0).getEntry();
        new MoveArcAndCloseByTrigger(arc, changeAngle, () -> entry.getDouble(MIN_POSSIBLE_ANGLE));
        calibrateArc.whenActive(new MoveArcUntilLowerLimitSwitch(arc));
    }
}

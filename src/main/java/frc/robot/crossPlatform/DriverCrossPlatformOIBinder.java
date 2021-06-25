package frc.robot.crossPlatform;

import static frc.robot.arc.ArcConstants.MIN_POSSIBLE_ANGLE;
import static frc.robot.crossPlatform.CrossPlatformConstants.CollectorConstantsA.TESTING_SPEED_COLLECTOR;
import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_RPM_WHILE_COLLECTING;
import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_RPM_WHILE_SHOOTING;
import static frc.robot.crossPlatform.CrossPlatformConstants.TriggerConstantsA.BALL_TRIGGER_RPM;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.arc.commands.CalibrateArc;
import frc.robot.arc.commands.MoveArcAndCloseByTrigger;
import frc.robot.arc.commands.MoveArcBySpeed;
import frc.robot.arc.commands.MoveArcUntilLowerLimitSwitch;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.commands.OpenBallTriggerPiston;
import frc.robot.ballTrigger.commands.SpinBallTriggerByRPM;
import frc.robot.ballTrigger.commands.SpinBallTriggerBySpeed;
import frc.robot.collector.Collector;
import frc.robot.collector.commands.CloseCollectorPistons;
import frc.robot.collector.commands.OpenCollectorPistons;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.SpinRevolverByRPM;
import frc.robot.revolver.commands.SpinRevolverBySpeed;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.SpinShooterByRPM;
import frc.robot.turret.Turret;
import frc.robot.vision.visionMainChallenge.Vision;
import onyxTronix.JoystickAxis;

public class DriverCrossPlatformOIBinder {

    public DriverCrossPlatformOIBinder(Collector collector, BallTrigger ballTrigger, Revolver revolver, Arc arc,
                                       Turret turret, Shooter shooter, Vision vision, Trigger collectAndLoadRevolver,
                                       Trigger shootBallTrigger, Trigger openCollector, JoystickAxis moveBallTrigger,
                                       Trigger changeAngle, Trigger calibrateArc) {
        collectAndLoadRevolver.whileActiveContinuous(new SpinRevolverByRPM(revolver,
                () -> REVOLVER_RPM_WHILE_COLLECTING)
        );

        shootBallTrigger.whileActiveContinuous(new ShootBall(shooter, ballTrigger, arc, turret, vision, revolver,
                shootBallTrigger));

        openCollector.whenActive(new OpenCollectorPistons(collector));
        openCollector.whenInactive(new CloseCollectorPistons(collector));

        moveBallTrigger.whileActiveContinuous(new SpinBallTriggerByRPM(ballTrigger, ()-> 2300));

        changeAngle.whenActive(new MoveArcAndCloseByTrigger(arc, changeAngle, arc::getTestAngle));
        calibrateArc.whenActive(new CalibrateArc(arc));
    }
}

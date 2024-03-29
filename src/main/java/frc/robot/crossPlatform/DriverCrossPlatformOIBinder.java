package frc.robot.crossPlatform;

import static frc.robot.crossPlatform.CrossPlatformConstants.CollectorConstantsA.TESTING_SPEED_COLLECTOR;
import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_RPM_WHILE_COLLECTING;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.collector.Collector;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.commands.SlowDriveTrainButton;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.WaitAndThenCalibrateRevolver;
import frc.robot.shooter.Shooter;
import frc.robot.vision.visionMainChallenge.Vision;
import frc.robot.yawControll.YawControl;
import onyxTronix.JoystickAxis;

public class DriverCrossPlatformOIBinder {

    public DriverCrossPlatformOIBinder(Collector collector, DriveTrain driveTrain, BallTrigger ballTrigger, Revolver revolver, Arc arc,
                                       YawControl yawControl, Shooter shooter, Vision vision,
                                       Trigger collectAndLoadRevolver, Trigger shootBallTrigger,
                                       JoystickAxis shootClose, Trigger shootAutonomously) {
        collectAndLoadRevolver.whileActiveOnce(new CollectAndSpinRevolver(collector, revolver,
                () -> REVOLVER_RPM_WHILE_COLLECTING, () -> TESTING_SPEED_COLLECTOR));
        new SlowDriveTrainButton(driveTrain, collectAndLoadRevolver);

        shootBallTrigger.whileActiveContinuous(new ShootBall(shooter, ballTrigger, arc, yawControl, vision, revolver,
                shootBallTrigger));
        shootBallTrigger.whenInactive(new WaitAndThenCalibrateRevolver(revolver));

        shootClose.whileActiveContinuous(new ShootBallClose(shooter, yawControl, ballTrigger, revolver));

        shootAutonomously.whenActive(new AutonomousShootBalls(ballTrigger, vision, arc, yawControl, shooter, revolver));
    }
}

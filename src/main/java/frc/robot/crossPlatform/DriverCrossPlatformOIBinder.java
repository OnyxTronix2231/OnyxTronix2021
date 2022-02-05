package frc.robot.crossPlatform;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.commands.WaitAndThenCalibrateRevolver;
import frc.robot.shooter.Shooter;
import frc.robot.yawControll.YawControl;
import onyxTronix.JoystickAxis;

public class DriverCrossPlatformOIBinder {

    public DriverCrossPlatformOIBinder(BallTrigger ballTrigger, Revolver revolver, Arc arc,
                                       YawControl yawControl, Shooter shooter,
                                       Trigger shootBallTrigger, ShootWhileDrivingCalc shootWhileDrivingCalc) {

        shootBallTrigger.whileActiveContinuous(new ShootBall(shooter, ballTrigger, arc, yawControl, revolver,
                shootBallTrigger, shootWhileDrivingCalc));
        shootBallTrigger.whenInactive(new WaitAndThenCalibrateRevolver(revolver));
    }
}

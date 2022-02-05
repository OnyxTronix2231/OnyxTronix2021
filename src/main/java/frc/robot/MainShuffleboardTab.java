package frc.robot;


import edu.wpi.first.cscore.HttpCamera;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.arc.Arc;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.revolver.Revolver;
import frc.robot.shooter.Shooter;
import frc.robot.yawControll.YawControl;

import java.util.Map;

public class MainShuffleboardTab {

    public MainShuffleboardTab(Shooter shooter, Revolver revolver, BallTrigger ballTrigger, Arc arc,
                               YawControl yawControl, HttpCamera limeLightFeed){

        Shuffleboard.getTab("Main").addBoolean("Arc on Target",
                arc::isOnTarget).withPosition(2,0);
        Shuffleboard.getTab("Main").addBoolean("Ball Trigger on Target",
                ballTrigger::isOnTarget).withPosition(3,0);
        Shuffleboard.getTab("Main").addBoolean("Revolver on Target",
                revolver::isOnTarget).withPosition(4,0);
        Shuffleboard.getTab("Main").addBoolean("Turret on Target",
                yawControl::isOnTarget).withPosition(5,0);
        Shuffleboard.getTab("Main").addBoolean("Shooter on Target",
                shooter::isOnTarget).withPosition(6,0);
        Shuffleboard.getTab("Main").add("LL", limeLightFeed).withPosition(6,1).withSize(3,3)
                .withProperties(Map.of("Show Crosshair", false));
    }
}

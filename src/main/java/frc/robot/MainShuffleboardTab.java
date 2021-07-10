package frc.robot;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.arc.Arc;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.revolver.Revolver;
import frc.robot.shooter.Shooter;
import frc.robot.vision.visionMainChallenge.InnerTarget;
import frc.robot.vision.visionMainChallenge.Vision;
import frc.robot.yawControll.YawControl;
import vision.limelight.Limelight;

import java.util.Map;

public class MainShuffleboardTab {

    public MainShuffleboardTab(Shooter shooter, Revolver revolver, BallTrigger ballTrigger, Arc arc, Vision vision,
                               YawControl yawControl, HttpCamera limeLightFeed, UsbCamera camera1, UsbCamera camera2){

        Shuffleboard.getTab("Main").addString("Vision target",
                ()-> vision.getChosenTarget() instanceof InnerTarget ? "Inner Target" : "Outer target")
                .withPosition(0,0);
        Shuffleboard.getTab("Main").addNumber("Distance to outer target",
                vision.getOuterTarget()::getAirDistanceTurretToTarget).withPosition(1,0);
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
        Shuffleboard.getTab("Main").add(camera1).withPosition(0,1).withSize(3,3)
                .withProperties(Map.of("Show Crosshair", false, "Show Contorls", false));
        Shuffleboard.getTab("Main").add(camera2).withPosition(3, 1).withSize(3,3)
                .withProperties(Map.of("Show Crosshair", false, "Show Contorls", false));
        Shuffleboard.getTab("Main").add("LL", limeLightFeed).withPosition(6,1).withSize(3,3)
                .withProperties(Map.of("Show Crosshair", false, "Show Contorls", false));
    }
}

package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.arc.Arc;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.revolver.Revolver;
import frc.robot.shooter.Shooter;
import frc.robot.vision.visionMainChallenge.InnerTarget;
import frc.robot.vision.visionMainChallenge.Vision;
import frc.robot.yawControll.YawControl;

public class MainShuffleboardTab {

    public MainShuffleboardTab(Shooter shooter, Revolver revolver, BallTrigger ballTrigger, Arc arc, Vision vision,
                               YawControl yawControl){

        Shuffleboard.getTab("Main").addBoolean("Shooter on Target",
                shooter::isOnTarget);
        Shuffleboard.getTab("Main").addBoolean("Arc on Target",
                arc::isOnTarget);
        Shuffleboard.getTab("Main").addBoolean("Revolver Sensor on Target",
                revolver::isHAllEffectOnTarget);
        Shuffleboard.getTab("Main").addBoolean("Revolver on Target",
                revolver::isOnTarget);
        Shuffleboard.getTab("Main").addBoolean("Ball Trigger on Target",
                ballTrigger::isOnTarget);
        Shuffleboard.getTab("Main").addBoolean("Turret on Target",
                yawControl::isOnTarget);
        Shuffleboard.getTab("Main").addString("Vision target",
                ()-> vision.getChosenTarget() instanceof InnerTarget ? "Inner Target" : "Outer target");
        Shuffleboard.getTab("Main").addNumber("Distance to outer target",
                vision.getOuterTarget()::getAirDistanceTurretToTarget);
    }
}

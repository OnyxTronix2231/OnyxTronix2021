package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.arc.DriverArcOiBinders;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.DriverBallTriggerOiBinder;
import frc.robot.collector.Collector;
import frc.robot.collector.DriverCollectorOiBinder;
import frc.robot.crossPlatform.DriverCrossPlatformOIBinder;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.DriveTrainOiBinder;
import frc.robot.revolver.Revolver;
import frc.robot.shooter.Shooter;
import frc.robot.turret.DriverTurretOiBinder;
import frc.robot.vision.visionMainChallenge.Vision;
import frc.robot.yawControll.YawControl;
import onyxTronix.JoystickAxis;

import static frc.robot.RobotConstants.DRIVER_JOYSTICK_PORT;

public class DriverOI {

    XboxController xboxController;

    public DriverOI() {
        xboxController = new XboxController(DRIVER_JOYSTICK_PORT);
    }

    public DriverOI withDriveTrainOi(DriveTrain driveTrain) {
        Trigger slowButton = new JoystickButton(xboxController, XboxController.Button.kB.value);
        new DriveTrainOiBinder(driveTrain, xboxController, slowButton);
        return this;
    }

    public DriverOI withCrossPlatformOi(Collector collector, BallTrigger ballTrigger, Revolver revolver, Arc arc,
                                        YawControl yawControl, Shooter shooter, Vision vision) {
        Trigger collectAndLoadRevolver = new JoystickButton(xboxController, XboxController.Button.kBumperRight.value);
        Trigger shootBall = new JoystickButton(xboxController, XboxController.Button.kBumperLeft.value);
        JoystickAxis shootClose = new JoystickAxis(xboxController, XboxController.Axis.kLeftTrigger.value);
        new DriverCrossPlatformOIBinder(collector, ballTrigger, revolver, arc, yawControl, shooter, vision,
                collectAndLoadRevolver, shootBall, shootClose);
        return this;
    }

    public DriverOI withArc(Arc arc){
        Trigger calibrateArc = new JoystickButton(xboxController, XboxController.Button.kStart.value);
        new DriverArcOiBinders(arc, calibrateArc);
        return this;
    }

    public DriverOI withCollector(Collector collector){
        JoystickAxis closeBallCollector = new JoystickAxis(xboxController, XboxController.Axis.kRightTrigger.value);
        new DriverCollectorOiBinder(collector, closeBallCollector);
        return this;
    }

    public DriverOI withTurret(YawControl yawControl){
        Trigger centerTurret = new JoystickButton(xboxController, XboxController.Button.kA.value);
        new DriverTurretOiBinder(yawControl, centerTurret);
        return this;
    }

    public DriverOI withBallTrigger(BallTrigger ballTrigger){
        Trigger open = new JoystickButton(xboxController, XboxController.Button.kY.value);
        new DriverBallTriggerOiBinder(ballTrigger, open);
        return this;
    }
}

package frc.robot;

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
import joysticks.ConsoleController;
import joysticks.OnyxXboxController;
import joysticks.PlayStation5Controller;
import onyxTronix.JoystickAxis;

import static frc.robot.RobotConstants.DRIVER_JOYSTICK_PORT;

public class DriverOI {

    ConsoleController controller;

    public DriverOI() {
            controller = new PlayStation5Controller(DRIVER_JOYSTICK_PORT);
    }

    public DriverOI withDriveTrainOi(DriveTrain driveTrain) {
        Trigger slowButton = new JoystickButton(controller, controller.getButtonRight());
        new DriveTrainOiBinder(driveTrain, controller, slowButton);
        return this;
    }

    public DriverOI withCrossPlatformOi(Collector collector, DriveTrain driveTrain, BallTrigger ballTrigger, Revolver revolver, Arc arc,
                                        YawControl yawControl, Shooter shooter, Vision vision) {
        Trigger collectAndLoadRevolver = new JoystickButton(controller, controller.getBumperRight());
        Trigger shootBall = new JoystickButton(controller, controller.getBumperLeft());
        JoystickAxis shootClose = new JoystickAxis(controller, controller.getLeftTrigger());

        new DriverCrossPlatformOIBinder(collector, driveTrain,ballTrigger, revolver, arc, yawControl, shooter, vision,
                collectAndLoadRevolver, shootBall, shootClose);
        return this;
    }

    public DriverOI withArc(Arc arc){
        Trigger calibrateArc = new JoystickButton(controller, controller.getStickRight());
        new DriverArcOiBinders(arc, calibrateArc);
        return this;
    }

    public DriverOI withCollector(Collector collector){
        JoystickAxis closeBallCollector = new JoystickAxis(controller, controller.getRightTrigger());
        new DriverCollectorOiBinder(collector, closeBallCollector);
        return this;
    }

    public DriverOI withTurret(YawControl yawControl, Vision vision){
        Trigger centerTurret = new JoystickButton(controller, controller.getButtonDown());
        new DriverTurretOiBinder(yawControl, vision,centerTurret);
        return this;
    }

    public DriverOI withBallTrigger(BallTrigger ballTrigger){
        Trigger open = new JoystickButton(controller, controller.getButtonUp());
        new DriverBallTriggerOiBinder(ballTrigger, open);
        return this;
    }
}

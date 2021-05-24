package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.BallTriggerComponents;
import frc.robot.collector.Collector;
import frc.robot.crossPlatform.DriverCrossPlatformOIBinder;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.DriveTrainOiBinder;
import frc.robot.revolver.Revolver;
import frc.robot.shooter.Shooter;
import frc.robot.turret.Turret;
import frc.robot.vision.visionMainChallenge.Vision;

import static frc.robot.RobotConstants.DRIVER_JOYSTICK_PORT;

public class DriverOI {

    XboxController xboxController;

    public DriverOI() {
        xboxController = new XboxController(DRIVER_JOYSTICK_PORT);

    }

    public DriverOI withDriveTrainOi(DriveTrain driveTrain) {
        new DriveTrainOiBinder(driveTrain, xboxController);
        return this;
    }

    public DriverOI createCrossPlatformOiBinder(Collector collector, BallTrigger ballTrigger, Revolver revolver, Arc arc,
                                             Turret turret, Shooter shooter, Vision vision) {
        Trigger collectAndLoadRevolver = new JoystickButton(xboxController, XboxController.Button.kBumperLeft.value);
        Trigger spinRevolverAndTrigger = new JoystickButton(xboxController, XboxController.Button.kY.value);
        Trigger spinRevolverAndTriggerThenOpenPiston = new JoystickButton(xboxController,
                XboxController.Button.kStart.value);
        Trigger shootBall = new JoystickButton(xboxController, XboxController.Button.kBumperRight.value);
        new DriverCrossPlatformOIBinder(collector, ballTrigger, revolver, arc, turret, shooter, vision,
                collectAndLoadRevolver, spinRevolverAndTrigger, spinRevolverAndTriggerThenOpenPiston, shootBall);
        return this;
    }
}

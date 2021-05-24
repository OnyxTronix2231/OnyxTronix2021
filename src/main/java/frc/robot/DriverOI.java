package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.BallTriggerComponents;
import frc.robot.collector.Collector;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.DriveTrainOiBinder;
import frc.robot.revolver.Revolver;
import frc.robot.shooter.Shooter;
import frc.robot.turret.Turret;
import frc.robot.vision.visionMainChallenge.Vision;

import static frc.robot.RobotConstants.DRIVER_JOYSTICK_PORT;

public class DriverOI {

    public DriverOI(DriveTrain driveTrain, Shooter shooter, Collector collector, Revolver revolver,
                    BallTrigger ballTrigger, Turret turret) {
        XboxController xboxController = new XboxController(DRIVER_JOYSTICK_PORT);
        Trigger resetButton = new JoystickButton(xboxController, XboxController.Button.kB.value);

        new DriveTrainOiBinder(driveTrain, collector, xboxController, resetButton, pathButton);
    }

    private static void createDriveTrainOi() {

    }
}

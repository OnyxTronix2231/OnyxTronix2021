package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.arc.DriverArcOiBinders;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.DriverBallTriggerOiBinder;
import frc.robot.crossPlatform.DriverCrossPlatformOIBinder;
import frc.robot.crossPlatform.ShootWhileDrivingCalc;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.DriverDriveTrainOiBinders;
import frc.robot.revolver.Revolver;
import frc.robot.shooter.Shooter;

import frc.robot.yawControll.YawControl;
import onyxTronix.JoystickAxis;

import static frc.robot.RobotConstants.DRIVER_JOYSTICK_PORT;

public class DriverOI {

    XboxController xboxController;

    public DriverOI() {
        xboxController = new XboxController(DRIVER_JOYSTICK_PORT);
    }

    public DriverOI withDriveTrainOi(DriveTrain driveTrain) {
        JoystickAxis leftJoystick = new JoystickAxis(xboxController, XboxController.Axis.kLeftY.value);
        JoystickAxis rightJoystick = new JoystickAxis(xboxController, XboxController.Axis.kRightX.value);
        new DriverDriveTrainOiBinders(driveTrain, leftJoystick, rightJoystick);
        return this;
    }

    public DriverOI withCrossPlatformOi(BallTrigger ballTrigger, Revolver revolver, Arc arc,
                                        YawControl yawControl, Shooter shooter, ShootWhileDrivingCalc shootWhileDrivingCalc) {
        Trigger shootBall = new JoystickButton(xboxController, XboxController.Button.kLeftBumper.value);
        new DriverCrossPlatformOIBinder(ballTrigger, revolver, arc, yawControl, shooter, shootBall, shootWhileDrivingCalc);
        return this;
    }

    public DriverOI withArc(Arc arc) {
        Trigger calibrateArc = new JoystickButton(xboxController, XboxController.Button.kStart.value);
        new DriverArcOiBinders(arc, calibrateArc);
        return this;
    }

    public DriverOI withBallTrigger(BallTrigger ballTrigger) {
        Trigger open = new JoystickButton(xboxController, XboxController.Button.kY.value);
        new DriverBallTriggerOiBinder(ballTrigger, open);
        return this;
    }
}

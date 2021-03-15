package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.DriveTrainOiBinder;
import frc.robot.turret.commands.MoveTurretToAngle;
import frc.robot.yawControll.YawControl;

import static frc.robot.RobotConstants.DRIVER_JOYSTICK_PORT;

public class DriverOI {

    public DriverOI(DriveTrain driveTrain, YawControl  yawControl) {
        XboxController xboxController = new XboxController(DRIVER_JOYSTICK_PORT);
        Trigger resetButton = new JoystickButton(xboxController, XboxController.Button.kB.value);

        new DriveTrainOiBinder(driveTrain, xboxController, resetButton);

        Trigger button1 = new JoystickButton(xboxController, XboxController.Button.kA.value);
        button1.whileActiveOnce(new MoveTurretToAngle(yawControl, ()-> 90));

        Trigger button2 = new JoystickButton(xboxController, XboxController.Button.kY.value);
        button2.whileActiveOnce(new MoveTurretToAngle(yawControl, ()-> 0));

    }
}

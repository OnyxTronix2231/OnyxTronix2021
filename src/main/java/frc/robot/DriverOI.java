package frc.robot;

import static frc.robot.RobotConstants.DRIVER_JOYSTICK_PORT;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.collector.Collector;
import frc.robot.commandGroups.DriverConveyorOIBinder;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.DriveTrainOiBinder;
import frc.robot.revolver.Revolver;

public class DriverOI {

  public DriverOI(DriveTrain driveTrain, BallTrigger ballTrigger, Collector collector, Revolver revolver) {
    XboxController xboxController = new XboxController(DRIVER_JOYSTICK_PORT);

    new DriveTrainOiBinder(driveTrain, xboxController);

    Trigger kBCollectAndLoad = new JoystickButton(xboxController, XboxController.Button.kB.value);
    Trigger kASpinRevolverAndTrigger = new JoystickButton(xboxController, XboxController.Button.kA.value);
    Trigger kXSpinRevolverAndTriggerThenOpenPiston = new JoystickButton(xboxController, XboxController.Button.kX.value);
    new DriverConveyorOIBinder(collector, ballTrigger, revolver, kBCollectAndLoad, kASpinRevolverAndTrigger,
            kXSpinRevolverAndTriggerThenOpenPiston);
  }
}

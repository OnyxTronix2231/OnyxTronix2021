package frc.robot;

import static frc.robot.RobotConstants.DEPUTY_JOYSTICK_PORT;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.arc.DeputeArcOiBinder;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.crossPlatform.DeputeCrossPlatformOi;
import frc.robot.climber.Climber;
import frc.robot.climber.ClimberDriverOIBinder;
import frc.robot.collector.Collector;
import frc.robot.collector.DeputeCollectorOiBinder;
import frc.robot.drivetrain.DeputeDriveTrainOiBinder;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.revolver.DeputeRevolverOiBinder;
import frc.robot.revolver.Revolver;
import frc.robot.shooter.Shooter;
import frc.robot.turret.DeputeTurretOiBinder;
import frc.robot.turret.Turret;
import joysticks.ConsoleController;
import joysticks.OnyxXboxController;
import onyxTronix.JoystickAxis;

public class DeputyOI {

    ConsoleController controller;

    public DeputyOI(){
        controller = new OnyxXboxController(DEPUTY_JOYSTICK_PORT);
    }

    public DeputyOI withClimber(Climber climber) {
        JoystickAxis clime = new JoystickAxis(controller, controller.getAxisLeftY());
        new ClimberDriverOIBinder(climber, clime);
        return this;
    }

    public DeputyOI withDriveTrain(DriveTrain driveTrain){
        Trigger slowButton = new JoystickButton(controller, controller.getButtonDown());
        new DeputeDriveTrainOiBinder(driveTrain, slowButton);
        return this;
    }

    public DeputyOI withTurret(Turret turret){
        JoystickAxis moveLeft = new JoystickAxis(controller, controller.getLeftTrigger());
        JoystickAxis moveRight = new JoystickAxis(controller, controller.getRightTrigger());
        new DeputeTurretOiBinder(turret, moveLeft, moveRight);
        return this;
    }

    public DeputyOI withArc(Arc arc){
        JoystickAxis moveArc = new JoystickAxis(controller, controller.getAxisRightY());
        Trigger calibrateArc = new JoystickButton(controller, controller.getCenterRight());
        new DeputeArcOiBinder(arc, moveArc, calibrateArc);
        return this;
    }

    public DeputyOI withRevolver(Revolver revolver){
        Trigger spinForward = new JoystickButton(controller, controller.getBumperRight());
        Trigger spinBackwards = new JoystickButton(controller, controller.getBumperLeft());
        Trigger resetRevolver = new JoystickButton(controller, controller.getButtonLeft());
        new DeputeRevolverOiBinder(revolver, spinForward, spinBackwards, resetRevolver);
        return this;
    }

    public DeputyOI withCollector(Collector collector){
        Trigger closeCollector = new JoystickButton(controller, controller.getButtonUp());
        Trigger ejectBall = new JoystickButton(controller, controller.getCenterLeft());
        new DeputeCollectorOiBinder(collector, closeCollector, ejectBall);
        return this;
    }

    public DeputyOI withCrossPlatform(BallTrigger ballTrigger, Shooter shooter){
        Trigger moveBallTrigger = new JoystickButton(controller, controller.getButtonRight());
        new DeputeCrossPlatformOi(ballTrigger, shooter, moveBallTrigger);
        return this;
    }
}

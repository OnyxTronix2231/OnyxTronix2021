package frc.robot;

import static frc.robot.RobotConstants.DEPUTY_JOYSTICK_PORT;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.Arc;
import frc.robot.arc.DeputeArcOiBinder;
import frc.robot.climber.Climber;
import frc.robot.climber.ClimberDriverOIBinder;
import frc.robot.collector.Collector;
import frc.robot.collector.DeputeCollectorOiBinder;
import frc.robot.revolver.DeputeRevolverOiBinder;
import frc.robot.revolver.Revolver;
import frc.robot.turret.DeputeTurretOiBinder;
import frc.robot.turret.Turret;
import onyxTronix.JoystickAxis;

public class DeputyOI {

    XboxController xboxController;

    public DeputyOI(){
        xboxController = new XboxController(DEPUTY_JOYSTICK_PORT);
    }

    public DeputyOI withClimber(Climber climber) {
        JoystickAxis clime = new JoystickAxis(xboxController, XboxController.Axis.kLeftY.value);
        new ClimberDriverOIBinder(climber, clime);
        return this;
    }

    public DeputyOI withTurret(Turret turret){
        JoystickAxis moveLeft = new JoystickAxis(xboxController, XboxController.Axis.kLeftTrigger.value);
        JoystickAxis moveRight = new JoystickAxis(xboxController, XboxController.Axis.kRightTrigger.value);
        new DeputeTurretOiBinder(turret, moveLeft, moveRight);
        return this;
    }

    public DeputyOI withArc(Arc arc){
        JoystickAxis moveArc = new JoystickAxis(xboxController, XboxController.Axis.kRightY.value);
        Trigger calibrateArc = new JoystickButton(xboxController, XboxController.Button.kStart.value);
        new DeputeArcOiBinder(arc, moveArc, calibrateArc);
        return this;
    }

    public DeputyOI withRevolver(Revolver revolver){
        Trigger spinForward = new JoystickButton(xboxController, XboxController.Button.kB.value);
        Trigger spinBackwards = new JoystickButton(xboxController, XboxController.Button.kA.value);
        Trigger resetRevolver = new JoystickButton(xboxController, XboxController.Button.kX.value);
        new DeputeRevolverOiBinder(revolver, spinForward, spinBackwards, resetRevolver);
        return this;
    }


    public DeputyOI withCollector(Collector collector){
        Trigger closeCollector = new JoystickButton(xboxController, XboxController.Button.kY.value);
        new DeputeCollectorOiBinder(collector, closeCollector);
        return this;
    }
}

package frc.robot;

import static frc.robot.RobotConstants.DEPUTY_JOYSTICK_PORT;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.climber.Climber;
import frc.robot.climber.ClimberDriverOIBinder;
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

}

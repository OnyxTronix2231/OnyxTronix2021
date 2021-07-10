package frc.robot.climber;

import frc.robot.climber.commands.ClimbBySpeed;
import onyxTronix.JoystickAxis;

import static frc.robot.climber.ClimberConstants.MAX_JOYSTICK_OUTPUT;

public class ClimberDriverOIBinder {

    public ClimberDriverOIBinder(Climber climber, JoystickAxis climbBySpeed) {
        climbBySpeed.whileActiveOnce(new ClimbBySpeed(climber, () -> climbBySpeed.getRawAxis() * MAX_JOYSTICK_OUTPUT));
    }
}

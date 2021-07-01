package frc.robot.climber;

import static frc.robot.climber.ClimberConstants.MAX_JOYSTICK_OUTPUT;

import frc.robot.climber.commands.ClimbBySpeed;
import onyxTronix.JoystickAxis;

public class ClimberDriverOIBinder {

    public ClimberDriverOIBinder(Climber climber, JoystickAxis climbBySpeed) {
        climbBySpeed.whileActiveOnce(new ClimbBySpeed(climber, () -> climbBySpeed.getRawAxis() * MAX_JOYSTICK_OUTPUT));
    }
}

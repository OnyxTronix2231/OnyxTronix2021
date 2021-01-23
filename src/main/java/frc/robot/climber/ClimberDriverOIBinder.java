package frc.robot.climber;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.climber.commands.ClimbBySpeed;

public class ClimberDriverOIBinder {
    public ClimberDriverOIBinder(Climber climber, Trigger climbBySpeed){
        climbBySpeed.whileActiveOnce( new ClimbBySpeed(climber, () -> 0.2 ));
    }

}

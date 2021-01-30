package frc.robot.climber;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.climber.commands.ClimbBySpeed;

import static frc.robot.climber.ClimberConstants.ClimberConstantsA.TESTING_SPEED;

public class ClimberDriverOIBinder {
    public ClimberDriverOIBinder(Climber climber, Trigger climbBySpeed){
        climbBySpeed.whileActiveOnce( new ClimbBySpeed(climber, () -> TESTING_SPEED));
    }

}

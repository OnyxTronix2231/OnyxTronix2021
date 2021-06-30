package frc.robot.climber;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.climber.commands.ClimbBySpeed;
import frc.robot.climber.commands.ClimbToDistance;

import static frc.robot.climber.ClimberConstants.ClimberConstantsA.DISTANCE;
import static frc.robot.climber.ClimberConstants.ClimberConstantsA.TESTING_SPEED;

public class ClimberDriverOIBinder {

    public ClimberDriverOIBinder(Climber climber, Trigger climbBySpeed //,Trigger climbByJoystick,
                                 /*XboxController climbJoystick, Trigger climbByDistance*/){
        climbBySpeed.whileActiveOnce( new ClimbBySpeed(climber, () -> TESTING_SPEED));

//        climbByDistance.whileActiveOnce(new ClimbToDistance(climber, () -> DISTANCE));
//
//        climbByJoystick.whileActiveContinuous(new ClimbBySpeed(climber, () -> climbJoystick.getY(GenericHID.Hand.kLeft)));
    }
}

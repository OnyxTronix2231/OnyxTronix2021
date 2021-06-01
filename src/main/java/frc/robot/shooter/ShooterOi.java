package frc.robot.shooter;

import frc.robot.shooter.commands.SpinShooterByRPM;
import frc.robot.shooter.commands.SpinShooterBySpeed;
import onyxTronix.JoystickAxis;

public class ShooterOi {

    public ShooterOi(Shooter shooter, JoystickAxis shootTrigger){
        shootTrigger.whileActiveContinuous(new SpinShooterByRPM(shooter, ()-> 4700));
    }
}

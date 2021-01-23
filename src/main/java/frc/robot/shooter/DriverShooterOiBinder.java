package frc.robot.shooter;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.shooter.commands.MoveShooterByRPM;
import frc.robot.shooter.commands.MoveShooterBySpeed;
import onyxTronix.JoystickAxis;

public class DriverShooterOiBinder {

    public DriverShooterOiBinder(Shooter shooter, JoystickAxis shootByPresentOutput, Trigger shootByRPM) {
        shootByPresentOutput.whileActiveContinuous(new MoveShooterBySpeed(shooter, shootByPresentOutput::getRawAxis));
        shootByRPM.whileActiveContinuous(new MoveShooterByRPM(shooter, () -> 4800));
    }
}

package frc.robot.shooter;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.shooter.commands.ShootByRPM;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;

public class DriverShooterOiBinder {

    public DriverShooterOiBinder(final UniqueAxisCache buttonJoystickAxisCache,
                                 UniqueButtonCache driveJoystickButtonCache, final Shooter shooter) {

        final JoystickAxis shootByVelocity = buttonJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftY.value);
        shootByVelocity.whileActiveContinuous(new ShootByRPM(shooter, () -> 17000));
    }
}

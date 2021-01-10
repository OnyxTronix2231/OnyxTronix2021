package frc.robot.shooter;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.shooter.commands.CloseShooterPiston;
import frc.robot.shooter.commands.OpenShooterPiston;
import frc.robot.shooter.commands.ShootByVelocity;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;

public class TestingShooterOi {

    public TestingShooterOi(final UniqueAxisCache buttonJoystickAxisCache,
                            UniqueButtonCache driveJoystickButtonCache, final Shooter shooter) {

        final JoystickAxis shootByVelocity = buttonJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftY.value);
        shootByVelocity.whileActiveContinuous(new ShootByVelocity(shooter, () -> 17000));

        final Trigger openPiston = driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickLeft.value);
        openPiston.whileActiveOnce(new OpenShooterPiston(shooter));

        final Trigger closePiston = driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickRight.value);
        closePiston.whileActiveOnce(new CloseShooterPiston(shooter));

    }
}

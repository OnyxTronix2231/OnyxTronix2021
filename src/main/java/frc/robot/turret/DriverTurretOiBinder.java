package frc.robot.turret;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.turret.commands.MoveTurretToAngle;

public class DriverTurretOiBinder {

    public DriverTurretOiBinder(Turret turret, Trigger centerTurret) {
        centerTurret.whileActiveContinuous(new MoveTurretToAngle(turret, () -> 0));
    }
}

package frc.robot.turret;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.turret.commands.MoveTurretByVision;
import frc.robot.turret.commands.MoveTurretToAngle;
import frc.robot.vision.visionMainChallenge.Vision;

public class DriverTurretOiBinder {

    public DriverTurretOiBinder(Turret turret, Vision vision, Trigger centerTurret) {
        centerTurret.whileActiveContinuous(new MoveTurretByVision(turret, vision));
    }
}

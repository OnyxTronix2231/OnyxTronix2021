package frc.robot.turret;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.turret.commands.MoveTurretByAngleAndKeep;
//import frc.robot.turret.commands.MoveTurretByVision;
import frc.robot.turret.commands.MoveTurretToAngle;
import frc.robot.turret.commands.MoveTurretToAngleAndKeep;

public class DriverTurretOiBinder {

    public DriverTurretOiBinder(Turret turret, Trigger centerTurret, Trigger tTurret, Trigger big, Trigger bigger) {
        centerTurret.whileActiveContinuous(new MoveTurretByAngleAndKeep(turret, ()->10));
        tTurret.whileActiveContinuous(new MoveTurretByAngleAndKeep(turret, ()->50));
        big.whileActiveContinuous(new MoveTurretByAngleAndKeep(turret, ()->90));
        bigger.whileActiveContinuous(new MoveTurretByAngleAndKeep(turret, ()->180));
    }
}

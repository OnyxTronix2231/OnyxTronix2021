package frc.robot.revolver;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.revolver.commands.MoveRevolverByRPM;
import frc.robot.revolver.commands.MoveRevolverBySpeed;

public class DriverRevolverOIBinder {
    public DriverRevolverOIBinder(Revolver revolver, Trigger moveRevolverByShootingRPM,
                                  Trigger moveRevolverByCollectingRPM) {
        moveRevolverByCollectingRPM.whileActiveContinuous(new MoveRevolverBySpeed(revolver, () -> 1394.53125));

        moveRevolverByShootingRPM.whileActiveContinuous(new MoveRevolverByRPM(revolver, () -> 3187.5));
    }
}

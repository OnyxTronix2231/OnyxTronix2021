package frc.robot.yawControll.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.yawControll.YawControl;
import frc.robot.yawControll.YawControl.TurretState;
//why does this class exist if never used?
public class SetTurretState extends InstantCommand {

    public SetTurretState(YawControl yawControl, TurretState turretState) {
        super(() -> yawControl.setTurretState(turretState));
    }
}

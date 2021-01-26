package frc.robot.yawControll.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.yawControll.YawControl;
import frc.robot.yawControll.YawControl.TurretState;

public class SetTurretState extends InstantCommand {

    public SetTurretState(YawControl yawControl, TurretState turretState) {
        super(()-> yawControl.setTurretState(turretState));
    }
}

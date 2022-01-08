package frc.robot.arc;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.commands.CalibrateArc;

public class DriverArcOiBinders {

    public DriverArcOiBinders(Arc arc, Trigger calibrateArc) {
        calibrateArc.whenActive(new CalibrateArc(arc));
    }
}

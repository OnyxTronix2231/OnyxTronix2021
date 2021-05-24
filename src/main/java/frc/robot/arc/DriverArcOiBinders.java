package frc.robot.arc;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.commands.CalibrateArc;
import frc.robot.arc.commands.MoveArcToAngle;

public class DriverArcOiBinders {

    public DriverArcOiBinders(Arc arc, Trigger changeAngle, Trigger calibrateArc) {
        NetworkTableEntry entry = Shuffleboard.getTab("Shooter").add("angle", 0).getEntry();
        changeAngle.whenActive(new MoveArcToAngle(arc, () -> entry.getDouble(0)));
        calibrateArc.whenActive(new CalibrateArc(arc));
    }
}
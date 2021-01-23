package frc.robot.arc;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.commands.MoveArcByAngle;

public class DriverArcOiBinders {

    public DriverArcOiBinders(Arc arc, Trigger changeAngle) {
        NetworkTableEntry entry = Shuffleboard.getTab("Arc").add("angle", 0).getEntry();
        changeAngle.whileActiveContinuous(new MoveArcByAngle(arc, () -> entry.getDouble(0)));
    }
}

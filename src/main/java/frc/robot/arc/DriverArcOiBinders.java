package frc.robot.arc;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.commands.CalibrateArc;
import frc.robot.arc.commands.MoveArcBySpeed;
import frc.robot.arc.commands.MoveArcToAngle;
import onyxTronix.JoystickAxis;

public class DriverArcOiBinders {

    public DriverArcOiBinders(Arc arc, Trigger changeAngle, Trigger calibrateArc, JoystickAxis moveArc) {
        NetworkTableEntry entry = Shuffleboard.getTab("Arc").add("angle", 0).getEntry();
        changeAngle.whenActive(new MoveArcToAngle(arc, () -> entry.getDouble(0)));
        calibrateArc.whenActive(new CalibrateArc(arc));
        moveArc.whileActiveContinuous(new MoveArcBySpeed(arc, moveArc::getRawAxis));
    }
}

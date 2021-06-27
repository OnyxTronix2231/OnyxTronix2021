package frc.robot.arc;

import static frc.robot.arc.ArcConstants.MIN_POSSIBLE_ANGLE;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.commands.CalibrateArc;
import frc.robot.arc.commands.MoveArcAndCloseByTrigger;
import frc.robot.arc.commands.MoveArcBySpeed;
import onyxTronix.JoystickAxis;

public class DriverArcOiBinders {

    public DriverArcOiBinders(Arc arc, Trigger changeAngle, Trigger calibrateArc, JoystickAxis moveArc) {
        NetworkTableEntry entry = Shuffleboard.getTab("Arc").add("angle", 0).getEntry();
        new MoveArcAndCloseByTrigger(arc, changeAngle, () -> entry.getDouble(MIN_POSSIBLE_ANGLE));
        calibrateArc.whenActive(new CalibrateArc(arc));
        moveArc.whileActiveContinuous(new MoveArcBySpeed(arc, moveArc::getRawAxis));
    }
}

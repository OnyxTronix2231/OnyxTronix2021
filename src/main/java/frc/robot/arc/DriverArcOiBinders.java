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

    public DriverArcOiBinders(Arc arc, Trigger calibrateArc) {
        calibrateArc.whenActive(new CalibrateArc(arc));
    }
}

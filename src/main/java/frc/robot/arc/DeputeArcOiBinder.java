package frc.robot.arc;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.arc.commands.CalibrateArc;
import frc.robot.arc.commands.MoveArcBySpeed;
import onyxTronix.JoystickAxis;

public class DeputeArcOiBinder {

    public DeputeArcOiBinder(Arc arc, JoystickAxis changeAngle, Trigger caibrateArc){
        changeAngle.whileActiveContinuous(new MoveArcBySpeed(arc, changeAngle::getRawAxis));
        caibrateArc.whenActive(new CalibrateArc(arc));
    }
}

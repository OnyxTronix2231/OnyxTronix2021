package frc.robot.turret;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.turret.commands.MoveTurretBySpeed;
import frc.robot.turret.commands.MoveTurretToAngleAndKeep;
import onyxTronix.JoystickAxis;

public class DriverTurretOiBinder {

    public DriverTurretOiBinder(Turret turret, JoystickAxis moveBySpeed, Trigger turretToAngle) {
          var entry = Shuffleboard.getTab("Turret").add("Angle To Move TO", 0).getEntry();
         turretToAngle.whileActiveOnce(new MoveTurretToAngleAndKeep(turret,()-> entry.getDouble(0)));
         moveBySpeed.whileActiveOnce(new MoveTurretBySpeed(turret, ()-> moveBySpeed.getRawAxis()));
//        moveBySpeed.whileActiveOnce(new MoveTurretByAngle(turret, ()->
//
//             moveBySpeed.getRawAxis() > 0 ? 225 : -35
//         ));

//        moveByJoystickAngle.whileActiveOnce(new MoveTurretByAngleContinuously(turret, moveByJoystickAngle::getRawAxis));
    }
}

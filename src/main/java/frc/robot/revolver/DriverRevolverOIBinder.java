package frc.robot.revolver;

import frc.robot.revolver.commands.SpinRevolverByRPM;
import onyxTronix.JoystickAxis;

public class DriverRevolverOIBinder {

  public DriverRevolverOIBinder(Revolver revolver, JoystickAxis moveRevolver) {

    moveRevolver.whileActiveContinuous(new SpinRevolverByRPM(revolver, () -> 120 * 1.5));
  }
}

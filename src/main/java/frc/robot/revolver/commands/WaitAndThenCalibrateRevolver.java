package frc.robot.revolver.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.revolver.Revolver;

import static frc.robot.revolver.RevolverConstants.WAIT_REVOLVER;

public class WaitAndThenCalibrateRevolver extends SequentialCommandGroup {

    public WaitAndThenCalibrateRevolver(Revolver revolver) {
        super(new WaitCommand(WAIT_REVOLVER),
                new SpinRevolverUntilLimitSwitch(revolver));
    }
}

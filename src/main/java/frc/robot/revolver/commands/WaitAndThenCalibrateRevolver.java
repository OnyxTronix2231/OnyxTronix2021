package frc.robot.revolver.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.revolver.Revolver;

public class WaitAndThenCalibrateRevolver extends SequentialCommandGroup {

    public WaitAndThenCalibrateRevolver(Revolver revolver){
        super(new WaitCommand(1),
                new SpinRevolverUntilLimitSwitch(revolver));
    }
}

package frc.robot.revolver.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.revolver.Revolver;

import static frc.robot.revolver.RevolverConstants.DELAY_CALIBRATE_TIME;

public class CalibrateRevolver extends SequentialCommandGroup{

    public CalibrateRevolver(Revolver revolver){
        super(
        new SpinRevolverUntilLimitSwitch(revolver),
                new WaitCommand(DELAY_CALIBRATE_TIME),
                new InstantCommand(revolver::resetEncoder));
    }
}

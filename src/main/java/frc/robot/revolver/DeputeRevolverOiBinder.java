package frc.robot.revolver;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.revolver.commands.SpinRevolverByRPM;

import static frc.robot.revolver.RevolverConstants.REVOLVER_RPM_WHILE_SPINING;

public class DeputeRevolverOiBinder {

    public DeputeRevolverOiBinder(Revolver revolver, Trigger spinForward, Trigger spinBackward){
        spinForward.whileActiveContinuous(new SpinRevolverByRPM(revolver, ()-> REVOLVER_RPM_WHILE_SPINING));
        spinBackward.whileActiveContinuous(new SpinRevolverByRPM(revolver, ()-> REVOLVER_RPM_WHILE_SPINING *  -1));
    }
}

package frc.robot.revolver.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.revolver.Revolver;

public class SpinRevolverUntilLimitSwitch extends SpinRevolverByRPM {

    private final Revolver revolver;

    public SpinRevolverUntilLimitSwitch(Revolver revolver){
        super(revolver, ()-> 30);
        this.revolver = revolver;
    }

    @Override
    public boolean isFinished() {
        return revolver.isHallEffectClosed();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        if (!interrupted) {
            revolver.resetEncoder();
        }
    }
}

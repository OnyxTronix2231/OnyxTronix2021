package frc.robot.revolver.commands;

import frc.robot.revolver.Revolver;

import static frc.robot.revolver.RevolverConstants.REVOLVER_SLOW_SPIN_RPM;

public class SpinRevolverUntilLimitSwitch extends SpinRevolverByRPM {

    private final Revolver revolver;

    public SpinRevolverUntilLimitSwitch(Revolver revolver){
        super(revolver, ()-> REVOLVER_SLOW_SPIN_RPM);
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

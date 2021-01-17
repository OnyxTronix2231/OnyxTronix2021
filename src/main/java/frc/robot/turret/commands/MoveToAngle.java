package frc.robot.turret.commands;

import frc.robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveToAngle extends MoveToAngleAndKeep {

    public MoveToAngle(Turret turret, DoubleSupplier angle) {
        super(turret, angle);
    }

    @Override
    public void initialize() {
        getTurret().initMoveToAngle(getAngle().getAsDouble());
    }

    @Override
    public void execute() {
        getTurret().updateMoveToAngle(getAngle().getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return getTurret().isOnTarget();
    }

    @Override
    public void end(boolean interrupted) {
        getTurret().stop();
    }

}






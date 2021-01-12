package frc.robot.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveByAngle extends CommandBase {
    private final Turret turret;
    private final DoubleSupplier getAngle;

    public MoveByAngle(Turret turret, DoubleSupplier getAngle) {
        this.turret = turret;
        this.getAngle = getAngle;
        addRequirements(turret);
    }

    @Override
    public void initialize() {
        turret.initMoveByAngle(getAngle.getAsDouble());
    }

    @Override
    public void execute() {
        turret.updateMoveByAngle(getAngle.getAsDouble());
    }

    @Override
    public void end(boolean interrupted){
        turret.stop();
    }
}

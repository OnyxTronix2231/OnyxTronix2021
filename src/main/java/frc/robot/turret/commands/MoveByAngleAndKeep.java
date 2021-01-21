package frc.robot.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveByAngleAndKeep extends CommandBase {
    protected final Turret turret;
    protected final DoubleSupplier angleSupplier;

    public MoveByAngleAndKeep(Turret turret, DoubleSupplier angleSupplier) {
        this.turret = turret;
        this.angleSupplier = angleSupplier;
        addRequirements(turret);
    }

    @Override
    public void initialize() {
        turret.initMoveByAngle(angleSupplier.getAsDouble());
    }

    @Override
    public void execute() {
        turret.updateMoveByAngle(angleSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        turret.stop();
    }

}

package frc.robot.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveTurretToAngleAndKeep extends CommandBase {
    protected final Turret turret;
    protected DoubleSupplier angle;


    public MoveTurretToAngleAndKeep(Turret turret, DoubleSupplier angle) {
        this.turret = turret;
        this.angle = angle;
        addRequirements(turret);
    }

    @Override
    public void initialize() {
        turret.initMoveToAngle(angle.getAsDouble());
    }

    @Override
    public void execute() {
        turret.updateMoveToAngle(angle.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        turret.stop();
    }


}


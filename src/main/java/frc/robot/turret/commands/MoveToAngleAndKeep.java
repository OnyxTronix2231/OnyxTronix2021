package frc.robot.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveToAngleAndKeep extends CommandBase {
    private final Turret turret;
    private final DoubleSupplier angle;

    public MoveToAngleAndKeep(Turret turret, DoubleSupplier angle) {
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

    public Turret getTurret(){
        return turret;
    }

    public DoubleSupplier getAngle(){
        return angle;
    }

}


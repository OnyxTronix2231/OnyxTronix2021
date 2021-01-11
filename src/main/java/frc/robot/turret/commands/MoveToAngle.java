package frc.robot.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveToAngle extends CommandBase {
    private final Turret turret;
    private final DoubleSupplier getAngle;

    public MoveToAngle(Turret turret, DoubleSupplier getAngle) {
        this.turret = turret;
        this.getAngle = getAngle;
        addRequirements(turret);
    }

    @Override
    public void execute() {
        turret.moveToAngle(getAngle.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
       turret.stop();
    }
}

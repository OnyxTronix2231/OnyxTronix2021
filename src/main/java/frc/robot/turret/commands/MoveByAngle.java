package frc.robot.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveByAngle extends MoveByAngleAndKeep {
    public MoveByAngle(Turret turret, DoubleSupplier angleSupplier) {
       super(turret,angleSupplier);
    }

    @Override
    public boolean isFinished() {
        return turret.isOnTarget();
    }

}

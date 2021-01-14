package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class ChangeAngleByPosition extends CommandBase {

    private final DoubleSupplier angle;
    private final Shooter shooter;
    
    public ChangeAngleByPosition(final Shooter shooter, final DoubleSupplier angle){
        this.angle = angle;
        this.shooter = shooter;
    }

    @Override
    public void execute() {
        shooter.changeAngleByPosition(angle.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopAngleMotor();
    }
}

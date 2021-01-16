package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class ChangeAngleByPosition extends CommandBase {

    private final DoubleSupplier angleSupplier;
    private final Shooter shooter;
    
    public ChangeAngleByPosition(final Shooter shooter, final DoubleSupplier angleSupplier){
        this.angleSupplier = angleSupplier;
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.changeAngleByPosition(angleSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopAngleMotor();
    }
}

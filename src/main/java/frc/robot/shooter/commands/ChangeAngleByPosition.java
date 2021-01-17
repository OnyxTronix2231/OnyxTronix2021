package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class ChangeAngleByPosition extends CommandBase {

    private final DoubleSupplier angleSupplier;
    private final Shooter shooter;
    
    public ChangeAngleByPosition(Shooter shooter, DoubleSupplier angleSupplier){
        this.angleSupplier = angleSupplier;
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.initMoveToAngle(angleSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopAngleMotor();
    }
}

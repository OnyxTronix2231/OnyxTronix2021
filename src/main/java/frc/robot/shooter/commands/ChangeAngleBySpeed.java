package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class ChangeAngleBySpeed extends CommandBase {

    private final DoubleSupplier speedSupplier;
    private final Shooter shooter;

    public ChangeAngleBySpeed(DoubleSupplier speedSupplier, Shooter shooter){
        this.speedSupplier = speedSupplier;
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.changeAngleBySpeed(speedSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopAngleMotor();
    }
}

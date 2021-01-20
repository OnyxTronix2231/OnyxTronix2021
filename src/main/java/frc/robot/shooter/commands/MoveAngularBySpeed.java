package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class MoveAngularBySpeed extends CommandBase {

    private final Shooter shooter;
    private final DoubleSupplier speedSupplier;

    public MoveAngularBySpeed(Shooter shooter, DoubleSupplier speedSupplier){
        this.speedSupplier = speedSupplier;
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.moveAngularMotorBySpeed(speedSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopAngularMotor();
    }
}
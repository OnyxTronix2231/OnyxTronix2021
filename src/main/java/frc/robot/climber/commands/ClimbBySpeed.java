package frc.robot.climber.commands;
import  edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.climber.Climber;

import java.util.function.DoubleSupplier;

public class ClimbBySpeed extends CommandBase {

    private final Climber climber;
    private final DoubleSupplier speedSupplier;

    public ClimbBySpeed(Climber climber, DoubleSupplier speedSupplier) {
        this.climber = climber;
        this.speedSupplier = speedSupplier;
        addRequirements(climber);
    }

    @Override
    public void execute() {
        climber.moveBySpeed(speedSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        climber.stopMotor();
    }
}

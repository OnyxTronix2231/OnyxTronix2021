package frc.robot.climber.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.climber.Climber;

import java.util.function.DoubleSupplier;

// why does this class exist?

public class ClimbToDistance extends CommandBase {

    private final Climber climber;
    private final DoubleSupplier distance;

    public ClimbToDistance(Climber climber, DoubleSupplier distance) {
        this.climber = climber;
        this.distance = distance;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.initClimbToDistance(distance.getAsDouble());
    }

    @Override
    public void execute() {
        climber.updateClimbToDistance(distance.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return climber.isOnTarget();
    }

    @Override
    public void end(boolean interrupted) {
        climber.stopMotor();
    }
}

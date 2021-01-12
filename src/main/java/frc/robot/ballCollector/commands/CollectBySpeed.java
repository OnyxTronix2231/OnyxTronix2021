package frc.robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ballCollector.BallCollector;

import java.util.function.DoubleSupplier;

public class CollectBySpeed extends CommandBase {

    private final BallCollector ballCollector;
    private final DoubleSupplier speedSupplier;

    public CollectBySpeed(final BallCollector ballCollector, final DoubleSupplier speedSupplier) {
        this.ballCollector = ballCollector;
        this.speedSupplier = speedSupplier;
        addRequirements(ballCollector);
    }

    @Override
    public void execute() {
        ballCollector.collectBySpeed(speedSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        ballCollector.stopMotor();
    }
}

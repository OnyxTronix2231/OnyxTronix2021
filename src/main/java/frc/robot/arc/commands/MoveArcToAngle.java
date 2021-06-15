package frc.robot.arc.commands;

import static frc.robot.arc.ArcConstants.MIN_POSSIBLE_ANGLE;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.arc.Arc;

import java.util.function.DoubleSupplier;

public class MoveArcToAngle extends CommandBase {

    private final Arc arc;
    private final DoubleSupplier angleSupplier;
    private boolean shouldReturnToHome;

    public MoveArcToAngle(Arc arc, DoubleSupplier angleSupplier) {
        this.angleSupplier = angleSupplier;
        this.arc = arc;
        addRequirements(arc);
        shouldReturnToHome = true;
    }

     MoveArcToAngle(Arc arc, DoubleSupplier angleSupplier, boolean shouldReturnToHome) {
        this.angleSupplier = angleSupplier;
        this.arc = arc;
        addRequirements(arc);
        this.shouldReturnToHome = shouldReturnToHome;
    }

    @Override
    public void initialize() {
        arc.initMoveToAngle(angleSupplier.getAsDouble());
    }

    @Override
    public void execute() {
        arc.updateMoveToAngle(angleSupplier.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return arc.isOnTarget();
    }

    @Override
    public void end(boolean interrupted) {
        arc.stop();
        if(shouldReturnToHome){
            shouldReturnToHome = false;
            CommandScheduler.getInstance().schedule(new MoveArcToAngle(arc,
                    () -> MIN_POSSIBLE_ANGLE, shouldReturnToHome));
        }
    }
}

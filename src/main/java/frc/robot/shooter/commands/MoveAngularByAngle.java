package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class MoveAngularByAngle extends CommandBase {

    private final Shooter shooter;
    private final DoubleSupplier angleSupplier;

    public MoveAngularByAngle(Shooter shooter, DoubleSupplier angleSupplier){
        this.angleSupplier = angleSupplier;
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.initAngualrMoveToAngle(angleSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopAngularMotor();
    }

    @Override
    public boolean isFinished() {
        return shooter.isAngularMotorOnTarget();
    }
}

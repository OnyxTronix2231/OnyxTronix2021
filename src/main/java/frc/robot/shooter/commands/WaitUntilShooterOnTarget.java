package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

public class WaitUntilShooterOnTarget extends CommandBase {

    private final Shooter shooter;

    public WaitUntilShooterOnTarget(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public boolean isFinished() {
        return shooter.isOnTarget();
    }
}

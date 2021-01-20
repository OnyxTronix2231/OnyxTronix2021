package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

public class WaitUntilShooterIsReadyToShoot extends CommandBase {

    private final Shooter shooter;

    public WaitUntilShooterIsReadyToShoot(Shooter shooter) {
        this.shooter = shooter;
    }

    @Override
    public boolean isFinished() {
        return shooter.isShooterReady();
    }
}

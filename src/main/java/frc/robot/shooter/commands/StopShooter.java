package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.shooter.Shooter;

public class StopShooter extends InstantCommand {

    private Shooter shooter;

    public StopShooter(final Shooter shooter) {
        this.shooter = shooter;
    }

    @Override
    public void initialize() {
        shooter.stopShooterMotor();
    }
}

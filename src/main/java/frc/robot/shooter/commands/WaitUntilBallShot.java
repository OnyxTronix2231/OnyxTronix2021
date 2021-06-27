package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

public class WaitUntilBallShot extends CommandBase {

    private final Shooter shooter;

    public WaitUntilBallShot(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.initIsBallShot();
    }

    @Override
    public boolean isFinished() {
        return shooter.updateIsBallShot();
    }
}
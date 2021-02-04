package frc.robot.flywheel.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.flywheel.Shooter;

public class WaitUntilBallShot extends CommandBase {

    private final Shooter shooter;

    public WaitUntilBallShot(Shooter shooter) {
        this.shooter = shooter;
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

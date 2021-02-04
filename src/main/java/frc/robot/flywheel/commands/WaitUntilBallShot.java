package frc.robot.flywheel.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.flywheel.Flywheel;

public class WaitUntilBallShot extends CommandBase {

    private final Flywheel flywheel;

    public WaitUntilBallShot(Flywheel flywheel) {
        this.flywheel = flywheel;
        addRequirements(flywheel);
    }

    @Override
    public void initialize() {
        flywheel.initIsBallShot();
    }

    @Override
    public boolean isFinished() {
        return flywheel.updateIsBallShot();
    }
}

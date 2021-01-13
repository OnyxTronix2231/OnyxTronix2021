package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

public class ChangeAngleByPosition extends CommandBase {

    private final double angle;
    private final Shooter shooter;
    
    public ChangeAngleByPosition(double angle, Shooter shooter){
        this.angle = angle;
        this.shooter = shooter;
    }

    @Override
    public void execute() {
        shooter.changeAngleByPosition(angle);
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopAngleMotor();
    }
}

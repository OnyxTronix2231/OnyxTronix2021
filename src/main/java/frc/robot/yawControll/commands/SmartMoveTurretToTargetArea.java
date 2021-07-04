package frc.robot.yawControll.commands;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.vision.visionMainChallenge.Vision;
import frc.robot.yawControll.YawControl;

public class SmartMoveTurretToTargetArea extends ConditionalCommand {
    public SmartMoveTurretToTargetArea(YawControl yawControl, Vision vision) {
        super(new InstantCommand(), new MoveTurretToTargetArea(yawControl),
                vision::hasTarget);
    }
}

package frc.robot.yawControll.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.turret.commands.MoveTurretByVision;
import frc.robot.vision.visionMainChallenge.Vision;
import frc.robot.yawControll.YawControl;

public class SmartMoveTurretByVision extends SequentialCommandGroup {
    public SmartMoveTurretByVision(YawControl yawControl, Vision vision) {
        super(
                new MoveTurretToTargetArea(yawControl),
                new MoveTurretByVision(yawControl, vision)
        );
    }
}

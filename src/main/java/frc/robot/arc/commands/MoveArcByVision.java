package frc.robot.arc.commands;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.arc.Arc;
import frc.robot.vision.visionMainChallenge.Vision;

public class MoveArcByVision extends ConditionalCommand {

    public MoveArcByVision(Arc arc, Vision vision) {
        super(new MoveArcToDistance(arc, ()-> vision.getChosenTarget().getAirDistanceTurretToTarget()),
            new InstantCommand(), vision::hasTarget);
    }
}

package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.vision.visionMainChallenge.Vision;

public class UpdateRobotPose extends InstantCommand {

    public UpdateRobotPose(DriveTrain driveTrain, Vision vision) {
        super(() ->driveTrain.resetOdometryToPose(vision.getCurrentPos()));
    }
}
